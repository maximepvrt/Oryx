package poc.backend.dao;

import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import org.jongo.FindOne;
import org.jongo.MongoCollection;

import poc.backend.db.MongoHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigo
 * Date: 7/24/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericDAO<T> {

    private Class<T> persistentClass;
    private String collectionName;

    private boolean firstRun = true;

    private void checkInit() {
        if (firstRun) {
            firstRun = false; // Before initiliaze(), in case initialize calls GenericDAO method (avoid inf. loops)
            try {
                initialize();
            } catch (Exception exc) {
            
            }
        }
    }

    public void initialize() throws Exception {};

    public static class Configuration {
        public Integer skip = null;
        public Integer limit = null;
        public String sortQuery = null;

        public Configuration setSkip(int skip) {
            this.skip = skip;
            return this;
        }

        public Configuration setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Configuration setSortQuery(String sortQuery) {
            this.sortQuery = sortQuery;
            return this;
        }

    }

    public GenericDAO(String collectionName, Class<T> targetClass) {
        this.persistentClass = targetClass;
        this.collectionName = collectionName;
        checkInit();
    }

    public enum SortOrder {
        ASCENDING, DESCENDING
    }

    public GenericDAO<T> ensureIndex(String indexName, SortOrder order) {
        MongoCollection coll = MongoHandler.get().getCollection(collectionName);
        coll.ensureIndex("{" + indexName +": "+(order==SortOrder.ASCENDING?"1":"-1") +"}");
        return this;
    }

    final public T get(String id) {
        if (id != null) {
            MongoCollection coll = MongoHandler.get().getCollection(collectionName);
            return coll.findOne(new org.bson.types.ObjectId(id)).as(persistentClass);
        }
        return null;
    }

    final public boolean saveOrUpdate(T object) {
        MongoCollection coll = MongoHandler.get().getCollection(collectionName);
        WriteResult res = coll.withWriteConcern(WriteConcern.FSYNC_SAFE).save(object);
        return res.getLastError().ok();
    }

    final public void saveOrUpdateAndForget(T object) {
        MongoCollection coll = MongoHandler.get().getCollection(collectionName);
        WriteResult res = coll.withWriteConcern(WriteConcern.UNACKNOWLEDGED).save(object);
    }

    final public boolean remove(String id) {
        MongoCollection coll = MongoHandler.get().getCollection(collectionName);
        WriteResult res = coll.withWriteConcern(WriteConcern.SAFE).remove(new org.bson.types.ObjectId(id));
        return res.getLastError().ok();
    }

    final public MongoCollection getCollection() {
        return MongoHandler.get().getCollection(collectionName);
    }

    final private org.jongo.Find mkFind(String query, Object... data) {
        MongoCollection coll = MongoHandler.get().getCollection(collectionName);
        org.jongo.Find find = data!=null?coll.find(query,data):coll.find(query);
        if (query != null && data != null) {
            return coll.find(query, data);
        } else if (query != null) {
            return coll.find(query);
        } else {
            return coll.find();
        }
    }

    final public Configuration defaultConfiguration = new Configuration().setLimit(100);

    final public List<T> findAll(String query, Object... data) {
        return find(defaultConfiguration, query, data);
//        MongoCollection coll = MongoHandler.get().getCollection(collectionName);
//        // org.jongo.Find find = data!=null?coll.find(query,data):coll.find(query);
//        org.jongo.Find find = mkFind(query, data);
//        Iterable<T> all = find.limit(50).as(this.persistentClass);
//        ArrayList<T> res = new ArrayList<T>();
//        for (T a: all) {
//            res.add(a);
//        }
//        return res;
    }

    final public List<T> find(GenericDAO.Configuration config, String query, Object... data) {
//        MongoCollection coll = MongoHandler.get().getCollection(collectionName);
//
//        org.jongo.Find find = mkFind(query, data);
//        if (config != null) {
//            if (config.skip != null) {
//                find.skip(config.skip);
//            }
//            if (config.limit != null) {
//                find.limit(config.limit);
//            }
//            if (config.sortQuery != null) {
//                if (config.sortQuery.trim().charAt(0) != '{') {
//                    find.sort("{" + config.sortQuery + "}");
//                } else {
//                    find.sort(config.sortQuery);
//                }
//            }
//        }
//
//        Iterable<T> all = find.as(this.persistentClass);

        final ArrayList<T> res = new ArrayList<T>();
        this.find(new Iterator<T>() {
            @Override
            public void iterate(T t) {
                res.add(t);
            }
        }, config, query, data);
        return res;
    }

    static public interface Iterator<T> {
        public void iterate(T t);
    }

    final public List<T> find(Iterator<T> iterator, GenericDAO.Configuration config, String query, Object... data) {
        MongoCollection coll = MongoHandler.get().getCollection(collectionName);

        org.jongo.Find find = mkFind(query, data);
        if (config != null) {
            if (config.skip != null) {
                find.skip(config.skip);
            }
            if (config.limit != null) {
                find.limit(config.limit);
            }
            if (config.sortQuery != null) {
                if (config.sortQuery.trim().charAt(0) != '{') {
                    find.sort("{" + config.sortQuery + "}");
                } else {
                    find.sort(config.sortQuery);
                }
            }
        }

        Iterable<T> all = find.as(this.persistentClass);
        ArrayList<T> res = new ArrayList<T>();
        for (T a: all) {
            iterator.iterate(a);
        }
        return res;
    }


    final public T findOne(String query, Object... data) {
        MongoCollection coll = MongoHandler.get().getCollection(collectionName);
        FindOne find = data!=null?coll.findOne(query,data):coll.findOne(query);
        T res = find.as(this.persistentClass);
        return res;
    }

    public long count() {
        MongoCollection coll = MongoHandler.get().getCollection(collectionName);
        return coll.count();
    }

    /**
     * Met à jour un objet à partir d'une map de données passée en paramètre.
     *
     * @param findQuery
     * @param findParameters
     * @param dataMap pour chaque entrée, la clé est le nom de la propriété de l'objet à mettre à jour, et la valeur est la nouvelle valeur
     */
    public void updateProperties(String findQuery, Object[] findParameters, Map<String, Object> dataMap) {
        org.jongo.Update upd = collection().update(findQuery, findParameters);
        for (String key: dataMap.keySet()) {
            upd.with("{$set: {'"+key+"': #}}", dataMap.get(key));
        }
    }

    public void updateAddToArray(String findQuery, Object[] findParameters, Map<String, Object> dataMap) {
        org.jongo.Update upd = collection().update(findQuery, findParameters);
        for (String key: dataMap.keySet()) {
            upd.with("{$push: {'"+key+"': #}}", dataMap.get(key));
        }
    }


    public MongoCollection collection() {
        return MongoHandler.get().getCollection(collectionName);
    }


}

package com.local.msvc.dao.intf;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface LocalDaoService {
	Document insert(String collectionName,Document document);
	void insertMany(String collectionName, List<Document> documents);
	UpdateResult updateOneDocEquelTo(String collectionName, String key, String value, Document document);
	UpdateResult updateManyDocEquelTo(String collectionName, String key, String value, Document document);
	DeleteResult deleteOneDocEquelTo(String collectionName, String key, String value);
	DeleteResult deleteManyDocEquelTo(String collectionName, String key, String value);
	DeleteResult deleteOneDoc(String collectionName, Document document);
	DeleteResult deleteManyDoc(String collectionName, Document document);
	Document findOne(String collectionName, Document document);
	List<Document>  findDocumentsCollection(String collectionName, Document document);
	boolean isExist(String collectionName, Document document);
	UpdateResult updateOneDocEqualToAnd(String collectionName, Iterable<Bson> filters, Document document);
	
	List<Document>  findDocCollectionGt(String collectionName,  String Key,Object value);
	
	List<Document>  findDocCollectionLt(String collectionName, String Key,Object value);
	
	List<Document>  findDocCollectionWithFilers(String collectionName,List<Bson> filters);
	
	List<Document>  findDocCollectionWithFilter(String collectionName, Bson filter) ;
	
	Document findOneWithSort(String collectionName, List<Bson> filters, Document sorter) ;

	List<Document> findColWithSort(String collectionName, List<Bson> filters, Document sorter);

	Document findDocumentByProjection(String collectionName, Document document, List<String> projectioneExpn) ;

	List<Document> findDocumentsByProjection(String collectionName, Document document, List<String> projectioneExpn);
	
	Document findAggregate(String collectionName, List<Bson> filters);
	
	List<Document> findAll(String collectionName);
	
	String getNextSequence(String sequenceName);
	
}

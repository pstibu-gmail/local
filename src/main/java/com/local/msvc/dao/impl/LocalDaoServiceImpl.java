package com.local.msvc.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.local.msvc.dao.intf.LocalDaoService;
import com.local.msvc.model.constants.ApplicationConstants;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Component
public class LocalDaoServiceImpl implements LocalDaoService {

	

	@Autowired
	private MongoDatabase database;


	public Document insert(String collectionName, Document document) {
		document.put(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE);
		database.getCollection(collectionName).insertOne(document);
		return document;

	}

	public void insertMany(String collectionName, List<Document> documents) {
		if (documents != null && documents.size() > 0) {
			documents.stream().forEach(map -> {
				map.put(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE);
			});
		}
		database.getCollection(collectionName).insertMany(documents);

	}

	public UpdateResult updateOneDocEquelTo(String collectionName, String key, String value, Document document) {
		Document updateQuery = new Document();
		updateQuery.append("$set", document);
		UpdateResult result = database.getCollection(collectionName).updateOne(Filters.eq(key, value), updateQuery);
		return result;

	}

	public UpdateResult updateManyDocEquelTo(String collectionName, String key, String value, Document document) {

		Document updateQuery = new Document();
		updateQuery.append("$set", document);

		UpdateResult result = database.getCollection(collectionName).updateMany(Filters.eq(key, value), updateQuery);
		return result;

	}

	public DeleteResult deleteOneDocEquelTo(String collectionName, String key, String value) {
		DeleteResult result = database.getCollection(collectionName).deleteOne(Filters.eq(key, value));
		return result;

	}

	public DeleteResult deleteManyDocEquelTo(String collectionName, String key, String value) {
		DeleteResult result = database.getCollection(collectionName).deleteMany(Filters.eq(key, value));
		return result;

	}

	public DeleteResult deleteOneDoc(String collectionName, Document document) {
		DeleteResult result = database.getCollection(collectionName).deleteOne(document);
		return result;

	}

	public DeleteResult deleteManyDoc(String collectionName, Document document) {
		DeleteResult result = database.getCollection(collectionName).deleteMany(document);
		return result;

	}

	public boolean isExist(String collectionName, Document document) {
		document.put(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE);
		Document foundTask = database.getCollection(collectionName).find(document).limit(1).first();
		if (null == foundTask) {
			return false;
		} else {
			return true;
		}
	}

	public Document findOne(String collectionName, Document document) {
		document.put(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE);
		Document documentFinded = database.getCollection(collectionName).find(document).limit(1).first();
		return documentFinded;
	}

	public List<Document> findDocumentsCollection(String collectionName, Document document) {
		document.put(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE);
		List<Document> documentsList = database.getCollection(collectionName).find(document)
				.into(new ArrayList<Document>());
		return documentsList;
	}

	public List<Document> findDocCollectionGt(String collectionName, String Key, Object value) {
		List<Bson> filterList = new ArrayList<>();
		filterList.add(Filters.eq(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE));
		filterList.add(Filters.gt(Key, value));
		List<Document> documentsList = database.getCollection(collectionName).find(Filters.and(filterList))
				.into(new ArrayList<Document>());
		return documentsList;
	}

	public List<Document> findDocCollectionLt(String collectionName, String Key, Object value) {
		List<Bson> filterList = new ArrayList<>();
		filterList.add(Filters.eq(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE));
		filterList.add(Filters.lt(Key, value));
		List<Document> documentsList = database.getCollection(collectionName).find(Filters.and(filterList))
				.into(new ArrayList<Document>());
		return documentsList;
	}

	public List<Document> findDocCollectionWithFilter(String collectionName, Bson filter) {
		List<Bson> filterList = new ArrayList<>();
		filterList.add(Filters.eq(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE));
		filterList.add(filter);
		List<Document> documentsList = database.getCollection(collectionName).find(Filters.and(filterList))
				.into(new ArrayList<Document>());
		return documentsList;
	}

	public List<Document> findDocCollectionWithFilers(String collectionName, List<Bson> filters) {
		filters.add(Filters.eq(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE));
		List<Document> documentsList = database.getCollection(collectionName).find(Filters.and(filters))
				.into(new ArrayList<Document>());
		return documentsList;
	}

	public UpdateResult updateOneDocEqualToAnd(String collectionName, Iterable<Bson> filters, Document document) {
		Document updateQuery = new Document();
		updateQuery.append("$set", document);
		UpdateResult result = database.getCollection(collectionName).updateOne(Filters.and(filters), updateQuery);
		return result;

	}

	public Document findOneWithSort(String collectionName, List<Bson> filters, Document sorter) {
		filters.add(Filters.eq(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE));
		Document documentFinded = database.getCollection(collectionName).find(Filters.and(filters)).sort(sorter)
				.limit(1).first();
		return documentFinded;
	}

	public List<Document> findColWithSort(String collectionName, List<Bson> filters, Document sorter) {
		filters.add(Filters.eq(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE));
		List<Document> documentsList = database.getCollection(collectionName).find(Filters.and(filters)).sort(sorter)
				.into(new ArrayList<Document>());
		return documentsList;
	}

	public Document findDocumentByProjection(String collectionName, Document document, List<String> projectioneExpn) {
		document.put(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE);
		Document documentFindedt = database.getCollection(collectionName).find(document)
				.projection(Projections.include(projectioneExpn)).limit(1).first();
		return documentFindedt;
	}

	public List<Document> findDocumentsByProjection(String collectionName, Document document, List<String> projectioneExpn) {
		document.put(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE);
		List<Document> documentsList = database.getCollection(collectionName).find(document)
				.projection(Projections.include(projectioneExpn)).into(new ArrayList<Document>());
		return documentsList;
	}
	
	public Document findAggregate(String collectionName, List<Bson> filters) {
		Document result = database.getCollection(collectionName).aggregate(filters).first();
		return result;
		
	}
	
	public List<Document> findAll(String collectionName){
		List<Document> result = database.getCollection(collectionName).find(Filters.eq(ApplicationConstants.DELETION_FLAG, ApplicationConstants.DELETION_FLAG_FALSE)).into(
				new ArrayList<Document>());
		return result;
	}

	@Override
	public String getNextSequence(String sequenceName) {
		Document document=database.runCommand(new Document("$eval","getNextSequence()"));
		return BigDecimal.valueOf(document.getDouble("retval")).toBigIntegerExact().toString();
	}
	
}

package com.work.firebase.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.work.firebase.dto.CreateResponse;
import com.work.firebase.dto.CustomerList;
import com.work.firebase.dto.DeleteResponse;
import com.work.firebase.dto.UpdateResponse;
import com.work.firebase.entity.Customer;

@Service
public class CustomerService {

	
	//Create Customer
	public CreateResponse createCustomer(Customer customer) throws InterruptedException, ExecutionException {

		Firestore fireStore = FirestoreClient.getFirestore();
		DocumentReference docRef = fireStore.collection("customer").document();

		customer.setId(docRef.getId());
		ApiFuture<WriteResult> apiFuture = docRef.set(customer);

		CreateResponse createResponse = new CreateResponse();

		createResponse.setCreateAt(apiFuture.get().getUpdateTime().toDate());
		createResponse.setId(customer.getId());
		return createResponse;

	}

	// Get All Customer 
	public CustomerList getCustomerList() throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("customer").get();
		List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
		List<Customer> customerList = list.stream().map((doc) -> doc.toObject(Customer.class))
				.collect(Collectors.toList());
		CustomerList customerListResponse = new CustomerList();
		customerListResponse.setList(customerList);
		return customerListResponse;
	}

	//Get Customer By ID
	public CustomerList getCustomerByKey(String key) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		System.out.println(key);
		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("customer").whereGreaterThanOrEqualTo("name", key)
				.whereLessThan("name", key + 'z').get();

		List<QueryDocumentSnapshot> documentSnapshots = apiFuture.get().getDocuments();

		List<Customer> customerList = documentSnapshots.stream().map(doc -> doc.toObject(Customer.class))
				.collect(Collectors.toList());

		CustomerList customerListResponse = new CustomerList();
		customerListResponse.setList(customerList);

		return customerListResponse;
	}

	//Update Customer By ID
	public UpdateResponse updateCustomer(Customer customer) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		System.out.println(customer);
		DocumentReference docRef = fireStore.collection("customer").document(customer.getId());
		docRef.set(customer);
		ApiFuture<WriteResult> apiFuture = docRef.set(customer);

		UpdateResponse updateResponse = new UpdateResponse();

		updateResponse.setUpdateAt(apiFuture.get().getUpdateTime().toDate());
		updateResponse.setId(customer.getId());
		return updateResponse;
	}

	
	//Delete Customer By ID
	public DeleteResponse deleteCustomer(String Id) throws InterruptedException, ExecutionException {
		Firestore fireStore = FirestoreClient.getFirestore();
		DocumentReference docRef = fireStore.collection("customer").document(Id);
		ApiFuture<WriteResult> apiFuture = docRef.delete();

		DeleteResponse response = new DeleteResponse();
		response.setDeleteDate(apiFuture.get().getUpdateTime().toDate());
		response.setStatus(true);
		return response;

	}

}

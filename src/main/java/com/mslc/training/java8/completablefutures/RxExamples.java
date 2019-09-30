//package com.mslc.training.java8.completablefutures;
//
//import static java.util.stream.Collectors.toList;
//
//import java.util.List;
//import java.util.Observable;
//import java.util.Observer;
//
//public class RxExamples {
//
//	private final List<Artist> savedArtists;
//	private final List<String> savedArtistNames;
//
//	public RxExamples(List<Artist> savedArtists) {
//		this.savedArtists = savedArtists;
//		savedArtistNames = savedArtists.stream().map(Artist::getName).collect(toList());
//	}
//
//	// BEGIN search
//	public Observable<Artist> search(String searchedName, String searchedNationality, int maxResults) {
//
//		return getSavedArtists() // <1>
//				.filter(name -> name.contains(searchedName)) // <2>
//				.flatMap(this::lookupArtist) // <3>
//				.filter(artist -> artist.getNationality() // <4>
//						.contains(searchedNationality))
//				.take(maxResults); // <5>
//	}
//	// END search
//
//	// ------------------ FAKE LOOKUP CODE ------------------
//	// Again, imaginary external web services
//
//	private Observable<String> getSavedArtists() {
//		return Observable.from(savedArtistNames);
//	}
//
//	private Observable<Artist> lookupArtist(String name) {
//		Artist required = savedArtists.stream().filter(artist -> artist.getName().equals(name)).findFirst().get();
//
//		return Observable.from(required);
//	}
//
//	// Purely for imported code sample
//	public void creationCodeSample() {
//		Observer<String> observer = null;
//
//// BEGIN completing_observable
//		observer.onNext("a");
//		observer.onNext("b");
//		observer.onNext("c");
//		observer.onCompleted();
//// END completing_observable
//
//// BEGIN error_observable
//		observer.onError(new Exception());
//// END error_observable
//
//	}
//
//}

package demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamMain {
	
	
	public static void main(String[] args) {
		
		Customer cus1 = new Customer("Rajaguru", "cus_00001", "usd");
		Customer cus2 = new Customer("Ramesh", "cus_00002", "inr");
		Customer cus3 = new Customer("Ramesh", "cus_00003", "inr");
		Charge charg1 = new Charge("ch_00001", 2000.00, true);
		Charge charg2 = new Charge("ch_00002", 5000.00, false);
		Charge charg3 = new Charge("ch_00003", 10000.00, true);
		Charge charg4 = new Charge("ch_0004", 15000.00, true);
		List<Charge> list1 = Arrays.asList(charg1,charg2,charg3,charg4);
		List<Charge> list2 = Arrays.asList(charg1,charg2,charg3);
		List<Charge> list3 = Arrays.asList(charg2,charg3,charg4);
		cus1.addCharges(list1);
		cus2.addCharges(list2);
		cus3.addCharges(list3);
		
		List<Customer> custList = Arrays.asList(cus1, cus2, cus3);
		
		//Stream examples -- non terminal methods
		
		//filter() -to filter the stream using predicate interface
				
		custList.stream()
				.filter(cust -> cust.getCurrency().equalsIgnoreCase("inr"))
				.forEach(System.out::println);
		
		//map() - to convert elements in stream to another object(Customer -> String) - function interface
		
		custList.stream()
			.map(cust -> cust.getCustomerName())
			.forEach(System.out::println);
		
		//flatMap() - used to map single element to multiple element - function interface
		
		custList.stream()
				.flatMap(cust -> cust.getCharges().stream())
				.forEach(System.out::println);
		
		//distinct() - used to remove duplicate elements 
		
		custList.stream()
				.map(cust -> cust.getCustomerName())
				.distinct()
				.forEach(System.out::println);;
		
		//limit() - used to limit the stream
		
		custList.stream()
				.limit(2)
				.forEach(System.out::println);
		
		//Terminal methods
		
		//anyMatch() 	- return type boolean
		//				- it use predicate interface return true if any of the element in stream return true
		
		System.out.println(custList.stream()
									.anyMatch(cust -> cust.getCurrency().equalsIgnoreCase("usd")));
		
		//allMatch() 	- return type boolean
		//				- same as anyMatch but return true only if all the element in stream is true
		
		System.out.println(custList.stream()
									.allMatch(cust -> cust.getCurrency().equalsIgnoreCase("usd")));
		
		//noneMatch() 	- return type boolean
		//				- reverse of anyMatch return true only if all element in stream return false
		
		System.out.println(custList.stream()
									.noneMatch(cust -> cust.getCurrency().equalsIgnoreCase("usd")));
		
		//collect() -  used to collect all the elements in the stream to save it in list
		
		List<String> custID = custList.stream()
									  .map(cust -> cust.getCustomerId())
									  .collect(Collectors.toList());
		custID.forEach(System.out::println);
		
		//count()		- return type long
		//				- to return count of stream
		
		System.out.println(custList.stream()
									.count());
		
		//findAny() 	- return type Optional<T> but using orElse it will return type will be the element of stream
		//				- return any element from the stream if present
		System.out.println(custList.stream()
									.findAny()
									.orElse(null));
		
		//findFirst()	- return first element of stream
		
		System.out.println(custList.stream()
									.findFirst()
									.orElse(null));
		
		//reduce()		- return type Optional<T>
		//				- reduce all element in a stream to single element(eg find total of all charges)
		
		Optional<Double> total = custList.stream()
										 .flatMap(cust -> cust.getCharges().stream())
										  .map(charge -> charge.getAmount())
										  .reduce((a,b) -> a+b);
		System.out.println("The total transaction amount was " + total.get());
		
		//Functional interface
		
		//Function - take one parameter and return another parameter - function - apply(T t)
		
		Function<Customer,String> getCustName = (cust) -> {
			
			return cust.getCustomerName();
		};
		
		custList.stream().map(getCustName).forEach(System.out::println);
		
		//Predicate - take one parameter and return boolean  - function - test(T t)
		
		Predicate<Customer> getCustwithUSD = (cust) -> {
			
			return cust.getCurrency().equalsIgnoreCase("inr");
		};
		custList.stream().filter(getCustwithUSD).forEach(System.out::println);
		
		//Consumer - takes one parameter and return nothing
		
		Consumer<Customer> print = (cust) -> {
			
			System.out.println(cust);
		};
		custList.stream().forEach(print);
		
		//Supplier - takes no parameter but return one parameter
		
		Supplier<Double> randomValue = () -> Math.random();
		  
        System.out.println(randomValue.get());
		
	}

}

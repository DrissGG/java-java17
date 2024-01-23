package java8.ex02;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.junit.Test;

import java8.data.Data;
import java8.data.domain.Customer;
import java8.data.domain.Order;

/**
 * Exercice 02 - Transformation
 */
public class Stream_02_Test {

	@Test
	public void test_map() throws Exception {

		List<Order> orders = new Data().getOrders();

		// TODO Trouver la liste des clients associés aux commandes
		List<Customer> result = orders.stream()
				.map(Order::getCustomer)
				.collect(Collectors.toList());

		assertThat(result, hasSize(8));
	}

	@Test
	public void test_map_count() throws Exception {

		List<Order> orders = new Data().getOrders();

		// TODO Compter le nombre de clients associés aux commandes
		long result = orders.stream()
				.map(Order::getCustomer)
				.count();

		assertThat(result, is(8L));
	}

	@Test
	public void test_map_distinct() throws Exception {

		List<Order> orders = new Data().getOrders();

		// TODO Trouver la liste des différents clients associés aux commandes (sans doublon)
		List<Customer> result = orders.stream()
				.map(Order::getCustomer)
				.distinct()
				.collect(Collectors.toList());

		assertThat(result, hasSize(2));
	}
	// explication détaillé
//		.orders.stream() crée un flux à partir de la liste des commandes (List<Order>).
//		.map(Order::getCustomer) transforme chaque commande en son client associé.
//		.distinct() élimine les clients en double, ne conservant que les clients uniques.
//		.collect(Collectors.toList()) collecte les résultats du flux dans une liste.

	@Test
	public void test_map_distinct_count() throws Exception {

		List<Order> orders = new Data().getOrders();

		// TODO Compter le nombre des différents clients associés aux commandes
		long result = orders.stream()
				.map(Order::getCustomer)
				.distinct().count();


		assertThat(result, is(2L));
	}

	@Test
	public void test_mapToDouble_sum() throws Exception {

		List<Order> orders = new Data().getOrders();

		/*
		 * TODO Calculer le chiffre d'affaires total de la pizzeria (somme des prix des
		 * commandes)
		 */
		double result = orders.stream()
				.mapToDouble(Order::getPrice).sum();

		assertThat(result, is(10900.0));
	}

	@Test
	public void test_mapToDouble_avg() throws Exception {

		List<Order> orders = new Data().getOrders();

		/*
		 * TODO Calculer le prix moyen des commandes de la pizzeria
		 */
		OptionalDouble result =  orders.stream().mapToDouble(Order::getPrice).average();

		assertThat(result.isPresent(), is(true));
		assertThat(result.getAsDouble(), is(1362.5));
	}
}
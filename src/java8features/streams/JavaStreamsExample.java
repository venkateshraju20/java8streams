package java8features.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreamsExample {

	public static void main(String[] args) throws IOException {

		// Integer Stream
		IntStream.range(1, 10).forEach(System.out::print);
		System.out.println();
		System.out.println("");

		// Integer Stream with skip
		// skip is to skip the no. elements from the top
		IntStream.range(1, 10).skip(3).forEach(x -> System.out.print(x));
		System.out.println();
		System.out.println("");

		// Integer Stream with sum
		System.out.print(IntStream.range(1, 10).sum());
		System.out.println();
		System.out.println("");

		// Stream.of, sort, find first
		Stream.of("hello", "java", "stream").sorted().findFirst().ifPresent(System.out::println);
		System.out.println("");

		// Stream from Arrays, sort, filter, and print
		String[] names = { "hello", "java", "stream", "streaming", "streamed" };
		Arrays.stream(names).filter(x -> x.startsWith("s")).sorted().forEach(System.out::println);
		System.out.println("");

		// avg of squares of an int array
		int[] values = { 2, 4, 6, 8, 10 };
		Arrays.stream(values).map(x -> x * x).average().ifPresent(System.out::println);
		System.out.println("");

		// Stream from list, filter and print
		List<String> list = Arrays.asList("hello", "java", "stream", "streaming", "streamed");
		list.stream().map(String::toUpperCase).filter(x -> x.startsWith("S")).forEach(System.out::println);
		System.out.println("");

		// Stream rows from a file then sort, filter and print
		Path path = Paths.get("{your_app_dir}/src/people.txt");
		Stream<String> people = Files.lines(path);
		people.sorted().filter(x -> x.length() > 12).forEach(System.out::println);
		people.close();
		System.out.println("");

		// Stream rows from a file and save into a list
		List<String> ppl = Files.lines(path).filter(x -> x.contains("es")).collect(Collectors.toList());
		ppl.forEach(x -> System.out.println(x));
		System.out.println("");

		// Stream rows from csv and count
		Path dataPath = Paths.get("{your_app_dir}/src/data.txt");
		Stream<String> csv = Files.lines(dataPath);
		int countRows = (int) csv.map(x -> x.split(",")).filter(x -> x.length == 3).count();
		System.out.println(countRows + " rows");
		csv.close();
		System.out.println("");

		// Stream rows from csv, parse data from rows
		Stream<String> csv2 = Files.lines(dataPath);
		csv2.map(x -> x.split(",")).filter(x -> x.length == 3).filter(x -> Integer.parseInt(x[1]) > 15)
				.forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
		csv2.close();
		System.out.println("");

		// Stream rows from csv, parse data from rows, save into hash map
		Stream<String> csv3 = Files.lines(dataPath);
		Map<String, Integer> map = new HashMap<>();
		map = csv3.map(x -> x.split(",")).filter(x -> x.length == 3).filter(x -> Integer.parseInt(x[1]) > 15)
				.collect(Collectors.toMap(x -> x[0], x -> Integer.parseInt(x[1])));
		csv3.close();
		for (String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
		System.out.println("");

		// stream reduce
		double totalValues = Stream.of(1.8, 3.7, 4.7).reduce(0.0, (Double a, Double b) -> a + b);
		System.out.println(totalValues);
		System.out.println("");

		// Stream - summary statistics
		IntSummaryStatistics summary = IntStream.of(2, 10, 30, 40).summaryStatistics();
		System.out.println(summary);

	}
}

public class HighloadApplication {

    public static void main(String[] args) {
		try (
				Scanner scanner = new Scanner(new File("people.v2.csv"));
				FileWriter fileWriter = new FileWriter("src/main/resources/db/migration/V002__create-data-usr.sql");
				PrintWriter printWriter = new PrintWriter(fileWriter);

				FileWriter fileWriter1 = new FileWriter("src/main/resources/db/migration/V003__create-data-usr.sql");
				PrintWriter printWriter1 = new PrintWriter(fileWriter1);

				FileWriter fileWriter2 = new FileWriter("src/main/resources/db/migration/V004__create-data-usr.sql");
				PrintWriter printWriter2 = new PrintWriter(fileWriter2);


				FileWriter fileWriter3 = new FileWriter("src/main/resources/db/migration/V005__create-data-usr.sql");
				PrintWriter printWriter3 = new PrintWriter(fileWriter3);

				FileWriter fileWriter4 = new FileWriter("src/main/resources/db/migration/V006__create-data-usr.sql");
				PrintWriter printWriter4 = new PrintWriter(fileWriter4);
		) {

			String pepper = "pepperyol56#gfcgjhn"; // secret key used by password encoding
			int iterations = 25;  // number of hash iteration
			int hashWidth = 16;      // hash width in bits

			Pbkdf2PasswordEncoder pbkdf2PasswordEncoder =
					new Pbkdf2PasswordEncoder(pepper, iterations, hashWidth, PBKDF2WithHmacSHA256);
			pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
			printWriter.println("INSERT INTO public.usr(first_name, second_name, birthdate, gender, biography, city, password) VALUES ");
			printWriter1.println("INSERT INTO public.usr(first_name, second_name, birthdate, gender, biography, city, password) VALUES ");
			printWriter2.println("INSERT INTO public.usr(first_name, second_name, birthdate, gender, biography, city, password) VALUES ");
			printWriter3.println("INSERT INTO public.usr(first_name, second_name, birthdate, gender, biography, city, password) VALUES ");
			printWriter4.println("INSERT INTO public.usr(first_name, second_name, birthdate, gender, biography, city, password) VALUES ");
			int i = 0;
			while (scanner.hasNextLine() && i < 200000) {
				String s = scanner.nextLine();
				String[] split = s.split(",");
				String secondName = split[0];
				String firstName = split[1];
				String birthdate = split[2];
				String gender = (System.currentTimeMillis() % 2) == 0 ? "муж" : "жен";
				String city = split[3];
				String password = pbkdf2PasswordEncoder.encode("123");
				String format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s'),", firstName, secondName, birthdate, gender, "Everythings", city, password);
				printWriter.println(format);
				++i;
			}
			String password = pbkdf2PasswordEncoder.encode("123");
			String format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s');", "Апельсин", "Цитрусов", "1980-08-31", "муж", "Everythings", "Москва", password);
			printWriter.println(format);
			while (scanner.hasNextLine() && i < 400000) {
				String s = scanner.nextLine();
				String[] split = s.split(",");
				String secondName = split[0];
				String firstName = split[1];
				String birthdate = split[2];
				String gender = (System.currentTimeMillis() % 2) == 0 ? "муж" : "жен";
				String city = split[3];
				password = pbkdf2PasswordEncoder.encode("123");
				format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s'),", firstName, secondName, birthdate, gender, "Everythings", city, password);
				printWriter1.println(format);
				++i;
			}
			password = pbkdf2PasswordEncoder.encode("123");
			format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s');", "Апельсин1", "Цитрусов1", "1980-08-31", "муж", "Everythings", "Москва", password);
			printWriter1.println(format);
			while (scanner.hasNextLine() && i < 600000) {
				String s = scanner.nextLine();
				String[] split = s.split(",");
				String secondName = split[0];
				String firstName = split[1];
				String birthdate = split[2];
				String gender = (System.currentTimeMillis() % 2) == 0 ? "муж" : "жен";
				String city = split[3];
				password = pbkdf2PasswordEncoder.encode("123");
				format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s'),", firstName, secondName, birthdate, gender, "Everythings", city, password);
				printWriter2.println(format);
				++i;
			}
			password = pbkdf2PasswordEncoder.encode("123");
			format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s');", "Апельсин2", "Цитрусов2", "1980-08-31", "муж", "Everythings", "Москва", password);
			printWriter2.println(format);
			while (scanner.hasNextLine() && i < 800000) {
				String s = scanner.nextLine();
				String[] split = s.split(",");
				String secondName = split[0];
				String firstName = split[1];
				String birthdate = split[2];
				String gender = (System.currentTimeMillis() % 2) == 0 ? "муж" : "жен";
				String city = split[3];
				password = pbkdf2PasswordEncoder.encode("123");
				format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s'),", firstName, secondName, birthdate, gender, "Everythings", city, password);
				printWriter3.println(format);
				++i;
			}
			password = pbkdf2PasswordEncoder.encode("123");
			format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s');", "Апельсин3", "Цитрусов3", "1980-08-31", "муж", "Everythings", "Москва", password);
			printWriter3.println(format);
			while (scanner.hasNextLine() && i < 1000000) {
				String s = scanner.nextLine();
				String[] split = s.split(",");
				String secondName = split[0];
				String firstName = split[1];
				String birthdate = split[2];
				String gender = (System.currentTimeMillis() % 2) == 0 ? "муж" : "жен";
				String city = split[3];
				password = pbkdf2PasswordEncoder.encode("123");
				format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s'),", firstName, secondName, birthdate, gender, "Everythings", city, password);
				printWriter4.println(format);
				++i;
			}
			password = pbkdf2PasswordEncoder.encode("123");
			format = String.format("('%s', '%s', '%s', '%s', '%s', '%s', '%s');", "Апельсин4", "Цитрусов4", "1980-08-31", "муж", "Everythings", "Москва", password);
			printWriter4.println(format);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
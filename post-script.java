public class HighloadApplication {

    public static void main(String[] args) {
		try (
				Scanner scanner = new Scanner(new File("post-script.txt"));
				FileWriter fileWriter = new FileWriter("src/main/resources/db/migration/V011__create-data-post.sql");
				PrintWriter printWriter = new PrintWriter(fileWriter);
		) {
			scanner.useDelimiter("\\r?\\n");

			printWriter.println("INSERT INTO post (usr_id, post) VALUES ");
			while (scanner.hasNext()) {
				String text = scanner.next().trim();
				String post = text.length() > 32 ? text.substring(0, 33) : text;
				String format = String.format("((SELECT id FROM usr ORDER BY RANDOM() LIMIT 1), '%s'),", post);
				printWriter.println(format);
			}
			String format = String.format("((SELECT id FROM usr ORDER BY RANDOM() LIMIT 1), '%s');", "End for start");
			printWriter.println(format);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
package comp;

public class Main {
    public static void main(String[] args) {
        try {
            AuthorDAO authorDAO = new AuthorDAO();
            //adaugam autori
            System.out.println("\n--------------------Autorii adaugati in baza de date:----------------------");
            String authorName1 = "Liviu Rebreanu";
            int authorId1 = authorDAO.create(authorName1);
            System.out.println("Author " + authorName1 + " with ID: " + authorId1 + " added!.");

            String authorName2 = "Mihail Drumes";
            int authorId2 = authorDAO.create(authorName2);
            System.out.println("Author " + authorName2 + " with ID: " + authorId2 + " added!");

            String authorName3 = "Mircea Eliade";
            int authorId3 = authorDAO.create(authorName3);
            System.out.println("Author " + authorName3 + " with ID: " + authorId3 + " added!");

            //actualizam numele
            System.out.println("\n--------------------Am actualziat baza de date:----------------------");
            Author author = authorDAO.findByName(authorName1);
            if (author != null) {
                authorDAO.update(author.getId(), "Miruna Varzar");
                System.out.println("Author " + authorName3 + " updated in Miruna Varzar!");

                authorDAO.update(author.getId(), "Liviu Ionel");
                System.out.println("Author " + authorName1 + " updated in Liviu Ionel!");
            }

           //cautam autorul
            authorId1 = 1;
            Author foundAuthor = authorDAO.findById(authorId1);
            if (foundAuthor != null) {
                System.out.println("Author with ID " + authorId1 + " is " + foundAuthor.getName() + ".");
            }

            System.out.println("\n--------------------Am sters din baza de date:----------------------");
            authorDAO.delete(authorId1);
            System.out.println("Author with ID " + authorId1 + " deleted");//sterg autorul dupa id

            Database.getInstance().getConnection().commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                Database.getInstance().getConnection().rollback(); //rollback daca avem eroare
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                Database.getInstance().getConnection().close();
            } catch (Exception closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}


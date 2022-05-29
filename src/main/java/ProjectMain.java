import java.sql.*;

public class ProjectMain {
    public static void main(String[] args) {


        String url = "jdbc:sqlite:src/main/resources/RecipesDB.db"; //path from content root
        try {

            // query that shows records needed from each recipe, this example uses recipe 3 as seen in R2I.idrecipe = 3"
            String query = "select I.name, I.unit, amount from ingredients I inner join recipe_ingredient R2I" +
                    " on I.idingredients = R2I.idingredient where R2I.idrecipe = 3";

            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            ResultSet rset = statement.executeQuery(query);

            while(rset.next()) {   // process each row
                String name = rset.getString("name");      // get name of ingredient
                double amount = rset.getDouble("amount");  // get amount of ingredient used in recipe
                String unit = rset.getString("unit");      // get unit of measurement for ingredient
                System.out.printf("%10.2f%10s%30s%n", amount, unit, name);
            }

/*            /////////////////////////////////////////////////////
            // functionality ideas for user searching database //
            /////////////////////////////////////////////////////
            //every time event on search bar happens
            //clear database results that may be shown
            //loop though array of ingredients
            //check if userinput.containsIgnoreCase(ingredients[i])
            //if it does use i+1 in query search -> database starts at 1
            //this example uses chicken which would is idingredient 14 as shown in ingredient table
            String searchRecipes = "select R.name from recipes R inner join recipe_ingredient R2I" +
                    " on R.idRecipes = R2I.idrecipe where R2I.idingredient = 14";

            rset = statement.executeQuery(searchRecipes);

            while(rset.next()) {   // process each row
                String name = rset.getString("name");      // get name of recipe
                System.out.println("\n" + name + "\n");
            }*/



/*            ////////////////////////////////////
            //To see all data in recipes table//
            ////////////////////////////////////
            String queryRecipeData = "select * from recipes";
            System.out.println("\nData from recipes table");
            System.out.printf("%5s%20s%20s%50s%n", "ID", "NAME", "serving size" , " link");
            rset = statement.executeQuery(queryRecipeData);

            while(rset.next()) {   // process each row
                int idRecipe = rset.getInt("idRecipes");
                String recipeName = rset.getString("name");
                int amountFed = rset.getInt("amountFed");
                String urlLink = rset.getString("link");
                System.out.printf("%5d%30s%5d%80s%n",idRecipe, recipeName, amountFed, urlLink);
            }

            ////////////////////////////////////
            //To see all data in ingredients table//
            ////////////////////////////////////
            String queryIngredientsData = "select * from ingredients";
            System.out.println("\nData from ingredients table");
            System.out.printf("%5s%30s%20s%n", "ID", "NAME", "measurement unit");
            rset = statement.executeQuery(queryIngredientsData);

            while(rset.next()) {   // process each row
                int idIngredients = rset.getInt("idIngredients");
                String ingredientName = rset.getString("name");
                String unit = rset.getString("unit");
                System.out.printf("%5d%30s%20s%n",idIngredients, ingredientName, unit);
            }

            //////////////////////////////////////////////
            //To see all data in recipe_ingredient table//
            //////////////////////////////////////////////
            String queryJoinedTableData = "select * from recipe_ingredient";
            System.out.println("\nData from recipe_ingredient table");
            System.out.printf("%5s%12s%15s%30s%n", "ID", "recipeID", "ingredientID" , "amount of ingredient");
            rset = statement.executeQuery(queryJoinedTableData);

            while(rset.next()) {   // process each row
                int idRecipeIngredient = rset.getInt("idrecipe_ingredient");
                int idRecipes = rset.getInt("idrecipe");
                int idIngredients = rset.getInt("idingredient");
                double amount = rset.getDouble("amount");

                System.out.printf("%5d%10d%10d%20.2f%n", idRecipeIngredient, idRecipes, idIngredients, amount);
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

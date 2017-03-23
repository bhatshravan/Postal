import java.io.*;
import java.net.*;
public class barcode
{
    public static void main(String args[])
    {
        /*
        String S="Shravan;;asjdhhk;;jhkjhh;;hkhkhh;;560061;;";
        String[] arr=S.split(";;");
        int l=arr.length;
        String y=arr[l-1];
        System.out.println(y);
         */		
        try
        {
            String S="Shravan;;asjdhhk;;jhkjhh;;hkhkhh;;560061;;";

            BufferedReader br = new BufferedReader(new FileReader("in.txt"));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                S=S.concat(sCurrentLine);
            }
            String[] arr=S.split(";;");
            int l=arr.length;
            String y=arr[l-1];
            System.out.println(y);

            PrintWriter out=new PrintWriter("out.txt");
            out.println(y);
            out.println("lets");
            out.close();
        }
        catch(IOException E)
        {
            System.out.println("ERROR");
        }
    }
/*
    public static String executePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", 
                "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", 
                Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");  

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
*/}
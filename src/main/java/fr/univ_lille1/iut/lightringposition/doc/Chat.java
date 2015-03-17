package fr.univ_lille1.iut.lightringposition.doc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("tchat")
public class Chat {
	@GET
	public String get(){
		String res="";
		String ligne;
		FileReader flux;
		BufferedReader fromFile = null;

		try {
			flux = new FileReader("webapp/send.txt");
			fromFile = new BufferedReader(flux);
			while ((ligne = fromFile.readLine()) != null) {
				res+=ligne;
			}
			fromFile.close();

		} catch (Exception e1) {
			System.err.println(e1.toString());

		}

		return res;
	}
	
	@Path("send")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void post(String s) {

		PrintWriter toFile = null;

		try {
			toFile = new PrintWriter("webapp/send.txt");
			toFile.write(s);
			toFile.close();


		} catch (Exception e1) {
			System.err.println(e1.toString());

		}



	}

}



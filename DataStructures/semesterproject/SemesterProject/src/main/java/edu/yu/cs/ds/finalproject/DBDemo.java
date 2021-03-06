package edu.yu.cs.ds.finalproject;

import net.sf.jsqlparser.JSQLParserException;

public class DBDemo {
	private static Database db = new Database();
	private ResultSet resultSet;
	public static void main(String[] args) throws JSQLParserException {
		db.execute("CREATE TABLE YCStudent (BannerID int, SSNum int UNIQUE, FirstName varchar(255), LastName varchar(255) NOT NULL, GPA decimal(1,2) DEFAULT 0.00, CurrentStudent boolean DEFAULT true, Class varchar(100), PRIMARY KEY (BannerID))");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Jacob, Naiman, 3.9, true, 800123456, 123, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Eli, Goldberg, 4.0, true, 800123457, 124, freshman)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Gilad, Felson, 3.7, true, 800123458, 125, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Jacob, Mendleson, 4.0, true, 800123459, 126, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Judah, Goldfeder, 3.2, true, 800123454, 127, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Jacob, Naiman, 3.9, true, 800123451, 122, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Eli, Goldberg, 4.0, true, 800123452, 121, freshman)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Gilad, Felson, 3.7, true, 800123453, 120, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Noam, Annenberg, 1.0, true, 800123460, 119, junior)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Daniel, Ginsberg, 1.7, false, 800123461, 118, graduated)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Netanel, Esraelian, 2.3, true, 800123462, 117, senior)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Nate, Saada, 3.9, false, 800123463, 116, graduated)");
		System.out.println("original copy of table YCStudent");
		ResultSet RS = db.execute("SELECT * FROM YCStudent");
		RS.print();
		System.out.println("selecting FirstName, lastName, GPA");
		RS = db.execute("SELECT FirstName, LastName, GPA FROM YCStudent");
		RS.print();
		System.out.println("selecting FirstName, lastName, average GPA, GPA");
		RS = db.execute("SELECT FirstName, LastName, AVG(GPA), GPA FROM YCStudent");
		RS.print();
		System.out.println("selecting FirstName, lastName, average GPA distinct, GPA");
		RS = db.execute("SELECT FirstName, LastName, AVG(Distinct GPA), GPA FROM YCStudent");
		RS.print();
		System.out.println("selecting FirstName, lastName, sum GPA distinct, sum GPA");
		RS = db.execute("SELECT FirstName, LastName, SUM(Distinct GPA), SUM(GPA) FROM YCStudent");
		RS.print();
		System.out.println("selecting FirstName, lastName, count GPA distinct, count GPA, min GPA, max GPA");
		RS = db.execute("SELECT FirstName, LastName, COUNT(Distinct GPA), COUNT(GPA), MIN(GPA), MAX(GPA) FROM YCStudent");
		RS.print();
		System.out.println("selecting FirstName, lastName, count GPA distinct, count GPA, min GPA, max GPA with strings");
		RS = db.execute("SELECT FirstName, LastName, COUNT(Distinct FirstName), COUNT(FirstName), MIN(FirstName), MAX(FirstName) FROM YCStudent");
		RS.print();
		System.out.println("selecting FirstName, LastName, GPA, Class ordering by class ascending, firstname descending, gpa ascending");
		RS = db.execute("SELECT FirstName, LastName, GPA, Class FROM YCStudent ORDER BY Class ASC, FirstName DESC, GPA ASC");
		RS.print();
		System.out.println("selecting distinct FirstName, LastName, GPA, Class ordering by firstname ascending");
		System.out.println("notice only 9 lines instead of 12");
		RS = db.execute("SELECT DISTINCT FirstName, LastName, GPA, Class FROM YCStudent ORDER BY FirstName ASC");
		RS.print();
		System.out.println("selecting distinct FirstName, LastName, GPA, Class ordering by firstname ascending where firstname equals jacob or gpa > 2");
		RS = db.execute("SELECT DISTINCT FirstName, LastName, GPA, Class FROM YCStudent WHERE FirstName = Jacob OR GPA > 2 ORDER BY FirstName ASC");
		RS.print();
		System.out.println("selecting distinct FirstName, LastName, GPA, Class ordering by firstname ascending where firstname equals jacob and lastname = naiman");
		RS = db.execute("SELECT DISTINCT FirstName, LastName, GPA, Class FROM YCStudent WHERE FirstName = Jacob AND LastName = Naiman ORDER BY FirstName ASC");
		RS.print();
		System.out.println("selecting distinct FirstName, LastName, GPA, Class ordering by firstname ascending where firstname > eli and gpa < 3.9");
		RS = db.execute("SELECT DISTINCT FirstName, LastName, GPA, Class FROM YCStudent WHERE FirstName > Eli AND GPA < 3.9 ORDER BY FirstName ASC");
		RS.print();
		System.out.println("selecting FirstName, LastName, GPA, Class ordering by bannerID ascending note banner id not on the table");
		RS = db.execute("SELECT FirstName, LastName, GPA, Class FROM YCStudent ORDER BY BannerID ASC");
		RS.print();
		System.out.println("selecting FirstName, LastName, GPA, Class ordering by firstname ascending where (GPA < 2 AND FirstName <> Noam) OR FirstName >= Jacob AND GPA > 3.0 OR GPA <= 4.0");
		RS = db.execute("SELECT FirstName, LastName, GPA, Class FROM YCStudent WHERE (GPA < 2 AND FirstName <> Noam) OR FirstName >= Jacob AND GPA > 3.0 AND GPA <= 4.0");
		RS.print();
		System.out.println("Moving on to update now");
		System.out.println("Full table first name ascending");
		RS = db.execute("SELECT * FROM YCStudent ORDER BY FirstName ASC");
		RS.print();
		db.execute("UPDATE YCStudent SET Class = 'Super Senior' WHERE LastName = Goldfeder OR FirstName = Jacob");
		System.out.println("updated class to super senior where first name = jacob or last name = goldfeder");
		RS = db.execute("SELECT * FROM YCStudent ORDER BY FirstName ASC");
		RS.print();
		db.execute("UPDATE YCStudent SET GPA = 3.0");
		System.out.println("updating all gpa to 3.0");
		RS = db.execute("SELECT * FROM YCStudent ORDER BY FirstName ASC");
		RS.print();
		System.out.println("Moving on to delete now");
		System.out.println("Full table first name ascending");
		RS = db.execute("SELECT * FROM YCStudent ORDER BY FirstName ASC");
		RS.print();
		db.execute("DELETE FROM YCStudent WHERE LastName = Goldfeder OR FirstName = Jacob");
		System.out.println("deleting where first name = jacob or last name = goldfeder");
		RS = db.execute("SELECT * FROM YCStudent ORDER BY FirstName ASC");
		RS.print();
		db.execute("DELETE FROM YCStudent");
		System.out.println("deleting all from YCStudent");
		RS = db.execute("SELECT * FROM YCStudent ORDER BY FirstName ASC");
		RS.print();
		
		System.out.println("now testing the btrees for where");
		System.out.println("creating btrees on firstname and gpa plus already an index on primary column bannerid");
		db.execute("CREATE INDEX FirstName_Index ON YCStudent (FirstName)");
		db.execute("CREATE INDEX GPA_Index ON YCStudent (GPA)");
		System.out.println("inserting the same original data back in");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Jacob, Naiman, 3.9, true, 800123456, 123, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Eli, Goldberg, 4.0, true, 800123457, 124, freshman)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Gilad, Felson, 3.7, true, 800123458, 125, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Jacob, Mendleson, 4.0, true, 800123459, 126, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Judah, Goldfeder, 3.2, true, 800123454, 127, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Jacob, Naiman, 3.9, true, 800123451, 122, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Eli, Goldberg, 4.0, true, 800123452, 121, freshman)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Gilad, Felson, 3.7, true, 800123453, 120, sophomore)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Noam, Annenberg, 1.0, true, 800123460, 119, junior)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Daniel, Ginsberg, 1.7, false, 800123461, 118, graduated)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Netanel, Esraelian, 2.3, true, 800123462, 117, senior)");
		db.execute("INSERT INTO YCStudent (FirstName, LastName, GPA, CurrentStudent, BannerID, SSNum, Class) VALUES (Nate, Saada, 3.9, false, 800123463, 116, graduated)");
		System.out.println("Full table first name ascending");
		RS = db.execute("SELECT * FROM YCStudent ORDER BY FirstName ASC");
		RS.print();
		System.out.println("testing where condition with btree selecting where FirstName > Gilad");
		RS = db.execute("SELECT FirstName, LastName, GPA FROM YCStudent WHERE FirstName > Gilad ORDER BY FirstName ASC");
		RS.print();
		db.execute("UPDATE YCStudent SET Class = 'Super Senior' WHERE GPA > 3.5");
		System.out.println("testing where condition with btree update set class = super senior where FirstName > Gilad or gpa =4.0");
		RS = db.execute("SELECT FirstName, LastName, GPA, Class FROM YCStudent WHERE FirstName > Gilad OR GPA = 4.0 ORDER BY FirstName ASC");
		RS.print();
		System.out.println("testing where condition with btree deleting where FirstName = Jacob or gpa = 3.7");
		db.execute("DELETE FROM YCStudent WHERE FirstName = Jacob OR GPA = 3.7");
		RS = db.execute("SELECT FirstName, LastName, GPA FROM YCStudent ORDER BY FirstName ASC");
		RS.print();
		System.out.println("testing with a where condition by select to show btree updated where firstname is greater than eli");
		RS = db.execute("SELECT FirstName, LastName, GPA FROM YCStudent WHERE FirstName > Eli ORDER BY FirstName ASC");
		RS.print();
		System.out.println("deleting all with where to show deleted from btree");
		db.execute("DELETE FROM YCStudent WHERE FirstName = Jacob OR GPA = 3.7");
		RS = db.execute("SELECT * FROM YCStudent WHERE GPA > 2.0 ORDER BY FirstName ASC");
		RS.print();
		System.out.println("end of DBDemo");
	}

}

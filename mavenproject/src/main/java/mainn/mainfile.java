package mainn;
import weka.core.neighboursearch.balltrees.*;

import weka.core.neighboursearch.*;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.core.converters.ConverterUtils.DataSource;
import java.util.*;


public class mainfile {
	public static NearestNeighbourSearch getNearestNeighbourSearch() {
		 return new BallTree();
		}
	
	static Attribute x =new Attribute("x");
	static Attribute y =new Attribute("y");
	static ArrayList<Attribute> att=new ArrayList<Attribute>(2);
	public static void main(String args[]) throws Exception {
		att.add(x);
		att.add(y);
		Instances centre = new Instances("Centre",att,0);
		BallTree t1=new BallTree(centre);
		long start = System.currentTimeMillis();
		int i=0;
		while(i<100000)
        {
            int input = rd_gen(0,2);
            switch(input){
                case 0:
                	insert(t1,centre);
                	break;
                case 1:
                	delete(t1,centre);
                	break;
                case 2:
                	search(t1,centre);
                	break;
                default:
                	System.out.println(" ");
                	break;
            }
            i++;
        }
		long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("TIME ELAPSED: "+elapsedTime+"ms");
	}
	
	public static void insert(BallTree t1,Instances centre) throws Exception{
			SparseInstance insta =new SparseInstance(2);
			insta.setValue(x, rd_gen(-100,100));
		    insta.setValue(y, rd_gen(-100,100));
		    insta.setDataset(centre);
		    centre.add(insta);
		    System.out.print("The instance = "+insta.value(x)+" ");
			System.out.println("The instance = "+insta.value(y));
			t1.setInstances(centre);
		
		System.out.println("Number of instances in Ball Tree : "+ t1.getInstances().size());
		System.out.println("the size of dataset : "+centre.size());
	}
	
	public static void delete(BallTree t1,Instances centre) throws Exception {
		if(centre.size()!=0) {
		int indx=rd_gen(0,centre.size()-1);
		System.out.println(indx);
		System.out.println("Deleted instance is : ");
		System.out.println(centre.get(indx).value(x));
		System.out.println(centre.get(indx).value(y));
		
		centre.delete(indx);
		System.out.println("Number of instances in Ball Tree : "+t1.getInstances().size());
		System.out.println("the size of dataset : "+centre.size());
		t1.setInstances(centre);
		}
		
	}
	public static void search(BallTree t1,Instances centre) throws Exception {
		
		if(centre.size()!=0) {
			SparseInstance insta2 =new SparseInstance(2);
			insta2.setValue(x, rd_gen(-100,100));
		    insta2.setValue(y, rd_gen(-100,100));
		    System.out.print("The instance = "+insta2.value(x)+" ");
			System.out.println("The instance = "+insta2.value(y));
			System.out.print("Nearest neighbor : ");
			System.out.print(t1.nearestNeighbour(insta2).value(x)+ " ");
			System.out.println(t1.nearestNeighbour(insta2).value(y));
		}
	}
	private static int rd_gen(int min, int max) {
		int input = (int)Math.floor(Math.random()*(max-min+1)+min);
		return input;
	}
}

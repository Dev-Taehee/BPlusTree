package edu.hanyang.submit;

import java.io.*;
import java.util.*;

import edu.hanyang.indexer.*;
import edu.hanyang.utils.*;

public class practice1 {

	public static void main(String[] args) throws Exception {
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		TestIntermediateList out = new TestIntermediateList();
		int[] x = {0, 1, 19, 1, 4, 0, 8, 13, 19, 2, 2, 4, 9, 4, 2, 2, 10};
		int[] x2 = {0, 1, 10, 1, 2, 10, 22, 3, 3, 1, 4, 16, 4, 2, 5, 12};
		for(int i=0; i<x.length; i++) {
			list.add(x[i]);
		}
		for(int i=0; i<x2.length; i++) {
			list2.add(x2[i]);
		}
		DocumentCursor dc = new TestDocCursor(list);
		DocumentCursor dc2 = new TestDocCursor(list2);
		and(dc,dc2,out);
		DocumentCursor dc3 = new TestDocCursor(out);
		while(dc3.is_eol()!=true) {
			System.out.println(dc3.get_docid());
			dc3.go_next();
		}
		
	}
	
	private static void and(DocumentCursor op1, DocumentCursor op2, IntermediateList out) throws IOException {
		while(op1.is_eol()!=true && op2.is_eol()!=true) {
			if(op1.get_docid()<op2.get_docid()) {
				op1.go_next();
			}else if(op1.get_docid()>op2.get_docid()) {
				op2.go_next();
			}else {
				out.put_docid(op1.get_docid());
				op1.go_next();
			}
		}
	}
}

package edu.hanyang.submit;

import java.io.*;
import java.util.*;

import edu.hanyang.indexer.*;
import edu.hanyang.utils.*;

public class practice2 {

	public static void main(String[] args) throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		TestIntermediatePositionalList out = new TestIntermediatePositionalList();
		int[] x = {0, 2, 2, 7, 1, 5, 1, 5, 12, 15, 18, 2, 3, 1, 7, 8, 3, 5, 5, 10, 15, 19, 20, 4, 3, 3,
				9, 16};
		int[] x2 = {0, 1, 18, 1, 3, 2, 7, 20, 2, 2, 3, 5, 3, 3, 2, 12, 23, 4, 4, 1, 6, 11, 17};
		for(int i=0; i<x.length; i++) {
			list.add(x[i]);
		}
		for(int i=0; i<x2.length; i++) {
			list2.add(x2[i]);
		}
		DocumentCursor dc = new TestDocCursor(list);
		DocumentCursor dc2 = new TestDocCursor(list2);
		and(dc,dc2,1,out);
		DocumentCursor dc3 = new TestDocCursor(out);
		while(dc3.is_eol()!=true) {
			System.out.println(dc3.get_docid());
			dc3.go_next();
		}
	}
	private static void and(DocumentCursor op1, DocumentCursor op2, int shift, IntermediatePositionalList out) throws IOException {
		while(op1.is_eol()!=true && op2.is_eol()!=true) {
			if(op1.get_docid()<op2.get_docid()) {
				op1.go_next();
			}else if(op1.get_docid()>op2.get_docid()) {
				op2.go_next();
			}else {
				PositionCursor q1 = op1.get_position_cursor();
				PositionCursor q2 = op2.get_position_cursor();
				while(q1.is_eol()!=true && q2.is_eol()!=true) {
					if(q1.get_pos()+shift<q2.get_pos()) {
						q1.go_next();
					}else if(q1.get_pos()+shift>q2.get_pos()) {
						q2.go_next();
					}else {
						out.put_docid_and_pos(op1.get_docid(), q1.get_pos());
						q1.go_next();
						q2.go_next();
					}
				}
				op1.go_next();
			}
		}
	}
}

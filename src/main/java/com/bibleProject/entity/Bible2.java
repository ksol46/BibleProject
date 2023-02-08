package com.bibleProject.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="bible2")
@Getter
@Setter
@ToString
public class Bible2 {
	@Id
	@Column(name="bible_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //성경식별자
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	private String category; //구약 신약 분류
	
	private String book; // 성경 책 이름
	
	private int chapter; // 성경 장 수
	
	private int verse; // 성경 절 수
	
	private String contents; // 성경 구절
	
	public static void insertBible () {
		
		BufferedReader fr = null;
		Reader r = null;
		
		try {
			r = new FileReader("C:\\project\\Bible\\bibletext.txt");
			fr = new BufferedReader(r);
			
			while (true) {
				
				String data = fr.readLine(); // 한 줄씩 읽어줌
				System.out.println(data);
				if (data == null) {
					break;
					
				}
				String first = data.replaceFirst(" ", "/");
				String second = first.replaceFirst(" ", "/");
				//System.out.println("첫번째 : " + second);
				
				String[] third = second.split("/");
				String category = third[0];
				String title = third[1];
				String content = third[2];
				//System.out.println("두번쨰 : " + category + "=" + title + "=" + content);

				//category : 구약, 신약 구분 완성
				
				String[] Array1 = title.split("[|가-힣]+");
				int search = title.indexOf(Array1[1]);
				String book = title.substring(0, search);
				//System.out.println("세번째 : " + book);
				
				String[] Array2 = Array1[1].split(":");
				int chapter = Integer.parseInt(Array2[0]);
				int verse = Integer.parseInt(Array2[1]);
				
				//chapter : 장, verse : 절 구분 완성
				
				//System.out.println("구분: " + category + " 책: " + book + " 장: " + chapter + " 절: " + verse + " 구절: " + content);
				//System.out.println("==========================================================================================================");
				
				//insert(category,book,chapter,verse,content);
				//System.out.println("insert 확인");
				
				Bible2 bible2 = new Bible2();
				bible2.setCategory(category);
				bible2.setBook(book);
				bible2.setChapter(chapter);
				bible2.setVerse(verse);
				bible2.setContents(content);
				
				
				
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	}


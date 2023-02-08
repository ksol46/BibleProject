package com.bibleProject.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibleProject.dto.BibleDto;
import com.bibleProject.entity.Bible;
import com.bibleProject.repository.BibleRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BibleService {
	//private final BibleRepository bibleRepository;
	
	/*
	// 성경 데이터 insert
	public Long insertBible(BibleDto bibleDto) {
		Bible bible = bibleDto.insertBible();
		bibleRepository.save(bible);
		
		return bible.getId();
	}
	*/
	
	/*
	try {
		BufferedReader fr = null;
		Reader r = null;
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
			
			bibleRepository.save(category,book,chapter,verse,content);
			System.out.println("insert 확인");
		}
		
		

	} catch (Exception e) {
		e.printStackTrace();
	}
}
*/
}


package com.bibleProject.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BibleController {
	private final BibleService bibleService;
}

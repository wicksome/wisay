package kr.opid.wisay;

import kr.opid.wisay.contorl.Setting;

public class Item {
	private String category_eng;
	private String category_kor;

	private String content_eng;
	private String person_eng;

	private String content_kor;
	private String person_kor;

	public Item() {
		this.category_kor = null;
		this.category_eng = null;
		this.content_eng = null;
		this.person_eng = null;
		this.content_kor = null;
		this.person_kor = null;
	}

	public Item(String category_eng, String category_kor, String content_eng,
			String person_eng, String content_kor, String person_kor) {
		this.category_eng = category_eng;
		this.category_kor = category_kor;
		this.content_eng = content_eng;
		this.person_eng = person_eng;
		this.content_kor = content_kor;
		this.person_kor = person_kor;
	}

	public String getContent() {
		String content = null;
		switch (Setting.getCurrentLanguage()) {
		case Lang.KOR:
			content = content_kor;
			break;
		case Lang.ENG:
			content = content_eng;
			break;
		}
		return content;
	}

	public String getPerson() {
		String content = null;
		switch (Setting.getCurrentLanguage()) {
		case Lang.KOR:
			content = person_kor;
			break;
		case Lang.ENG:
			content = person_eng;
			break;
		}
		return content;
	}

	public String getCategory() {
		String content = null;
		switch (Setting.getCurrentLanguage()) {
		case Lang.KOR:
			content = category_kor;
			break;
		case Lang.ENG:
			content = category_eng;
			break;
		}
		return content;
	}

	public String getCategory_kor() {
		return category_kor;
	}

	public String getCategory_eng() {
		return category_eng;
	}

	public String getContent_eng() {
		return content_eng;
	}

	public String getPerson_eng() {
		return person_eng;
	}

	public String getContent_kor() {
		return content_kor;
	}

	public String getPerson_kor() {
		return person_kor;
	}

	public void setSubject_kor(String subject_kor) {
		this.category_kor = subject_kor;
	}

	public void setSubject_eng(String subject_eng) {
		this.category_eng = subject_eng;
	}

	public void setContent_eng(String content_eng) {
		this.content_eng = content_eng;
	}

	public void setPerson_eng(String person_eng) {
		this.person_eng = person_eng;
	}

	public void setContent_kor(String content_kor) {
		this.content_kor = content_kor;
	}

	public void setPerson_kor(String person_kor) {
		this.person_kor = person_kor;
	}

}

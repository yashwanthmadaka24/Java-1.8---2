package com.infomover.training.java8.part2;

import com.infomover.training.java8.ch10.command.Close;
import com.infomover.training.java8.ch10.command.Editor;
import com.infomover.training.java8.ch10.command.Macro;
import com.infomover.training.java8.ch10.command.Open;
import com.infomover.training.java8.ch10.command.Save;
import com.infomover.training.java8.ch10.command.SimpleEditor;

public class Ch10App0CommandPattern {

	public static void main(String[] args) {

		Editor editor = new SimpleEditor();
		Macro m = new Macro();
		m.record(new Open(editor));
		m.record(new Save(editor));
		m.record(new Close(editor));
		
		m.record(() -> editor.open());
		m.record(() -> editor.save());
		m.record(() -> editor.close());
		
		m.record(editor::open);
		m.record(editor::save);
		m.record(editor::close);
		
		//m.run();
		
		
		
	}
}

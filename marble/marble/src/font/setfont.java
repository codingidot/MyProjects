package font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class setfont {

	//TTF 폰트 로드 및 사이즈 수정 메소드
	public Font fontSetting(float size, String type) {
		String urlebold = "NanumSquareEB.ttf";
		InputStream ebold = this.getClass().getResourceAsStream(urlebold);
		String urlbold = "NanumSquareB.ttf";
		InputStream bold = this.getClass().getResourceAsStream(urlbold);
		String urlregular = "NanumSquareR.ttf";
		InputStream regular = this.getClass().getResourceAsStream(urlregular);
		String urllight = "NanumSquareL.ttf";
		InputStream light = this.getClass().getResourceAsStream(urllight);
		Font sizeFont=null;
		
		try {
			switch (type) {
				case "ebold":
					Font font = Font.createFont(Font.TRUETYPE_FONT, ebold);
					sizeFont=font.deriveFont(size);
					break;
					
				case "bold":
					font = Font.createFont(Font.TRUETYPE_FONT, bold);
					sizeFont=font.deriveFont(size);
					break;
					
				case "regular":
					font = Font.createFont(Font.TRUETYPE_FONT, regular);
					sizeFont=font.deriveFont(size);
					break;
		
				case "light":
					font = Font.createFont(Font.TRUETYPE_FONT, light);
					sizeFont=font.deriveFont(size);
					break;
				}
			}
			catch(IOException | FontFormatException e)
			{
				e.printStackTrace();
			}
		return sizeFont;
	}
	
	
}

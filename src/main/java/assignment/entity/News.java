package assignment.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {
	private String id;
	private String title;
	private String content;
	@Default
	private String image = "news.png";
	@Default
	private Date postedDate = new Date();
	private String author;
	private int viewCount;
	private String categoryId;
	private boolean home;
}

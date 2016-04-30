package so.dun.mybatis.po;

public class Teacher {

	private int id;
	private String name;

	public Teacher() {
	}

	public Teacher(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + "]";
	}

}

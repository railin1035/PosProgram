package DB;

public class detailDTO {
	private int oId; //상세번호
	private int cId; //주문번호
	private int pId; //메뉴아이디
	private int amount; //수량
	private int cost; //금액
	
	public detailDTO() {}
	
	public detailDTO(int oid, int cid, int pid, int amo, int co) {
		oId = oid;
		cId = cid;
		pId = pid;
		amount = amo;
		cost = co;
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}

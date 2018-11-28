package csh.entity;

/**
 * @author ��ˬ
 * @create 2018-11-08 18:04
 **/
public class PageView {
    //private List<Order> records;// ��¼
    private Long totalrecordnumber;// �ܼ�¼��
    private Integer startindex;// ��һҳ
    private Integer endindex;// ���һҳ
    private Integer totalpagenumber;// ��ҳ��
    private Integer currentpage;// ��ǰҳ

    public PageView( Long totalrecordnumber, int currentpage,
                    int maximum, int viewperpage)
    // ���캯��
    {
        //this.records = records;
        this.totalrecordnumber = totalrecordnumber;
        this.currentpage = currentpage;
        //�����ҳ��
        totalpagenumber = (int) (totalrecordnumber % maximum == 0 ? totalrecordnumber / maximum : totalrecordnumber / maximum + 1);
        setIndex(currentpage, viewperpage, totalpagenumber);
    }

//    public List<Order> getRecords() {
//        return records;
//    }

    public Long getTotalrecordnumber() {
        return totalrecordnumber;
    }

    public Integer getStartindex() {
        return startindex;
    }

    public Integer getEndindex() {
        return endindex;
    }

    public Integer getTotalpagenumber() {
        return totalpagenumber;
    }

    public Integer getCurrentpage() {
        return currentpage;
    }

    //�����ҳ�� ��ʾҳ�� ��ǰҳ�� ��һҳ ���һҳ
    public void setIndex(int currentpage, int viewperpage, int totalpagenumber) {
        if (viewperpage >= totalpagenumber) {
            startindex = 1;
            endindex = totalpagenumber;
        } else {
            if (currentpage <= viewperpage / 2) {
                startindex = 1;
                endindex = viewperpage;
            } else if ((currentpage + viewperpage / 2) > totalpagenumber) {
                startindex = totalpagenumber - viewperpage + 1;
                endindex = totalpagenumber;
            } else {
                startindex = currentpage - (viewperpage - 1) / 2;
                endindex = currentpage + viewperpage / 2;
            }
        }
    }
}

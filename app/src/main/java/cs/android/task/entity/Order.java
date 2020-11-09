package cs.android.task.entity;

import java.util.Date;

public class Order {
private String ID;
private Date createDate;
private String Location;

public String getID () {
    return ID;
}

public void setID (String ID) {
    this.ID = ID;
}

public Date getCreateDate () {
    return createDate;
}

public void setCreateDate (Date createDate) {
    this.createDate = createDate;
}

public String getLocation () {
    return Location;
}

public void setLocation (String location) {
    Location = location;
}
}

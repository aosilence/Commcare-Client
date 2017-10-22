package commcare.capstone.comcare.model.datacollection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yuan on 18/10/17.
 */
@DatabaseTable(tableName = "genogramobj")
public class GenogramObj {

    public static final String RELATIONSHIP_PARENT = "Parent";
    public static final String RELATIONSHIP_SPOUSE = "Spouse";
    public static final String RELATIONSHIP_SIBLING = "Sibling";
    public static final String RELATIONSHIP_CHILD = "Child";
    public static final String RELATIONSHIP_MAIN = "Self";

    public static final String SEX_MALE = "Male";
    public static final String SEX_FEMALE = "Female";

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true) long id;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    DataCollectionForm parent;
    @DatabaseField(columnName = "full_name", canBeNull = true) String fullName;
    @DatabaseField(columnName = "age", canBeNull = true) String age;
    @DatabaseField(columnName = "occupation", canBeNull = true) String occupation;
    @DatabaseField(columnName = "salary", canBeNull = true) String salary;
    @DatabaseField(columnName = "illness", canBeNull = true) String illness;
    @DatabaseField(columnName = "relation", canBeNull = true) String relation;
    @DatabaseField(columnName = "sex", canBeNull = true) String sex;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    GenogramObj relateTo;
    int x;
    int y;


    public GenogramObj getRelateTo() {
        return relateTo;
    }

    public void setRelateTo(GenogramObj relateTo) {
        this.relateTo = relateTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DataCollectionForm getParent() {
        return parent;
    }

    public void setParent(DataCollectionForm parent) {
        this.parent = parent;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

package commcare.capstone.comcare.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import commcare.capstone.comcare.model.datacollection.GenogramObj;

import static android.R.attr.left;
import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by yuan on 20/10/17.
 */
public class GenoDrawView extends View {

    Logger LOG = LoggerFactory.getLogger(GenoDrawView.class);

    ArrayList<GenogramObj> parentGeno;
    private Paint outerPaint;
    ArrayList<GenogramObj> genoParents = new ArrayList<GenogramObj>();
    ArrayList<GenogramObj> genoChilds = new ArrayList<GenogramObj>();
    ArrayList<GenogramObj> genoSpouse = new ArrayList<GenogramObj>();
    ArrayList<GenogramObj> genoSiblings = new ArrayList<GenogramObj>();
    GenogramObj mainGen;
    int xOffset = 100;
    int xOffsetinc = 200;


    public GenoDrawView(DataCollectionGenoFormActivity a, ArrayList<GenogramObj> parentGeno) {
        super(a);

        this.parentGeno = parentGeno;

        LOG.debug("DEMO");
        //demo();

        for (GenogramObj genChild : this.parentGeno) {
            if (genChild.getRelation().equals(GenogramObj.RELATIONSHIP_MAIN)) {
                mainGen = genChild;
            }
        }


        for (GenogramObj genChild : this.parentGeno) {
            if (genChild.getRelateTo() != null && genChild.getRelateTo().equals(mainGen))
            {
                if (genChild.getRelation().equals(GenogramObj.RELATIONSHIP_PARENT))
                {
                    genoParents.add(genChild);
                }
                else if (genChild.getRelation().equals(GenogramObj.RELATIONSHIP_SPOUSE))
                {
                    genoSpouse.add(genChild);
                }
                else if (genChild.getRelation().equals(GenogramObj.RELATIONSHIP_CHILD))
                {
                    genoChilds.add(genChild);
                }
                else if (genChild.getRelation().equals(GenogramObj.RELATIONSHIP_SPOUSE))
                {
                    genoSiblings.add(genChild);
                }
            }
        }
        Collections.sort(genoChilds, new Comparator<GenogramObj>() {
            @Override
            public int compare(GenogramObj lhs, GenogramObj rhs) {
                return Integer.parseInt(lhs.getAge()) > Integer.parseInt(rhs.getAge()) ? -1 :
                        Integer.parseInt(lhs.getAge()) < Integer.parseInt(rhs.getAge()) ? 1 : 0;
            }
        });

        if (mainGen.getSex().equals(GenogramObj.SEX_FEMALE))
        {
            xOffset = -100;
            xOffsetinc = -200;
        }

        outerPaint = new Paint();
        outerPaint.setColor(Color.BLACK);
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setTextSize(10f);
    }

    private void demo() {
        parentGeno = new ArrayList<GenogramObj>();
        GenogramObj geno = new GenogramObj();
        geno.setRelation(GenogramObj.RELATIONSHIP_MAIN);
        geno.setFullName("James Oliver Kilahor");
        geno.setAge("20");
        geno.setOccupation("Construction Worker");
        geno.setSalary("2000");
        geno.setIllness("Heart Disease");
        geno.setSex(GenogramObj.SEX_MALE);
        //geno.setSex(GenogramObj.SEX_FEMALE);
        parentGeno.add(geno);

        GenogramObj pgeno = new GenogramObj();
        pgeno.setRelation(GenogramObj.RELATIONSHIP_PARENT);
        pgeno.setRelateTo(geno);
        pgeno.setFullName("Father");
        pgeno.setAge("20");
        pgeno.setOccupation("Construction Worker");
        pgeno.setSalary("2000");
        pgeno.setIllness("Heart Disease");
        pgeno.setSex(GenogramObj.SEX_MALE);
        //geno.setSex(GenogramObj.SEX_FEMALE);
        parentGeno.add(pgeno);

        pgeno = new GenogramObj();
        pgeno.setRelation(GenogramObj.RELATIONSHIP_PARENT);
        pgeno.setFullName("Mother");
        pgeno.setRelateTo(geno);
        pgeno.setAge("20");
        pgeno.setOccupation("Construction Worker");
        pgeno.setSalary("2000");
        pgeno.setIllness("Heart Disease");
        //geno.setSex(GenogramObj.SEX_MALE);
        pgeno.setSex(GenogramObj.SEX_FEMALE);
        parentGeno.add(pgeno);

        pgeno = new GenogramObj();
        pgeno.setRelation(GenogramObj.RELATIONSHIP_SPOUSE);
        pgeno.setFullName("Spouse");
        pgeno.setRelateTo(geno);
        pgeno.setAge("20");
        pgeno.setOccupation("Construction Worker");
        pgeno.setSalary("2000");
        pgeno.setIllness("Heart Disease");
        //geno.setSex(GenogramObj.SEX_MALE);
        pgeno.setSex(GenogramObj.SEX_FEMALE);
        parentGeno.add(pgeno);

        pgeno = new GenogramObj();
        pgeno.setRelation(GenogramObj.RELATIONSHIP_CHILD);
        pgeno.setFullName("Child 1");
        pgeno.setRelateTo(geno);
        pgeno.setAge("20");
        pgeno.setOccupation("Construction Worker");
        pgeno.setSalary("2000");
        pgeno.setIllness("Heart Disease");
        //geno.setSex(GenogramObj.SEX_MALE);
        pgeno.setSex(GenogramObj.SEX_FEMALE);
        parentGeno.add(pgeno);

        pgeno = new GenogramObj();
        pgeno.setRelation(GenogramObj.RELATIONSHIP_CHILD);
        pgeno.setFullName("Child 2");
        pgeno.setRelateTo(geno);
        pgeno.setAge("30");
        pgeno.setOccupation("Construction Worker");
        pgeno.setSalary("2000");
        pgeno.setIllness("Heart Disease");
        //geno.setSex(GenogramObj.SEX_MALE);
        pgeno.setSex(GenogramObj.SEX_FEMALE);
        parentGeno.add(pgeno);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        drawBorder(canvas, this.getWidth(), this.getHeight());
        int sideLength = 20;
        int centrex = this.getWidth() / 2;
        int centrey = this.getHeight() / 2;

        int x =  centrex - (sideLength / 2);
        int y =  centrey - (sideLength / 2);

        if (mainGen.getSex().equals(GenogramObj.SEX_FEMALE))
        {
            xOffset = -100;
            xOffsetinc = -200;
        }
        else
        {
            xOffset = 100;
            xOffsetinc = 200;
        }

        generateGroup(mainGen, canvas, x, y, sideLength);
        if (genoParents.size() == 1)
        {
            generateGroup(genoParents.get(0), canvas, x, y-75, sideLength);
            drawChildLine(canvas, x, y, sideLength);
        }
        else if (genoParents.size() == 2)
        {
            if(genoParents.get(0).getSex().equals(GenogramObj.SEX_MALE))
            {
                generateGroup(genoParents.get(0), canvas, x-100, y-75, sideLength);
                generateGroup(genoParents.get(1), canvas, x+100, y-75, sideLength);

            }
            else
            {
                generateGroup(genoParents.get(1), canvas, x-100, y-75, sideLength);
                generateGroup(genoParents.get(0), canvas, x+100, y-75, sideLength);
            }
            drawCoupleChildLine(canvas, x, y, sideLength);
        }


        for (GenogramObj genChild : genoChilds)
        {
            generateGroup(genChild, canvas, x + xOffset, y+75, sideLength);
            drawParentLine(canvas, x + xOffset, y+75, sideLength);
            xOffset += xOffsetinc;
        }

        if (genoSpouse.size() > 0)
        {
            if(genoSpouse.get(0).getSex().equals(GenogramObj.SEX_MALE)) {
                generateGroup(genoSpouse.get(0), canvas, x-200, y, sideLength);
                drawSpouseLine(canvas, x-200, y, sideLength, true);
            }
            else
            {
                generateGroup(genoSpouse.get(0), canvas, x+200, y, sideLength);
                drawSpouseLine(canvas, x+200, y, sideLength, false);
            }
        }
    }

    private void drawBorder(Canvas canvas, int width, int height) {
        RectF outerRect = new RectF(0, 0, width-1, height-1);
        canvas.drawRect(outerRect, outerPaint);
    }

    private void drawParentLine(Canvas canvas, int x, int y, int sideLength) {
        canvas.drawLine(x+sideLength/2, y, x+sideLength/2, y-75+sideLength+20, outerPaint);
    }

    private void drawSpouseLine(Canvas canvas, int x, int y, int sideLength, boolean left) {
        canvas.drawLine(x+sideLength/2, y+sideLength, x+sideLength/2, y+sideLength+20, outerPaint);
        if (left)
        {
            canvas.drawLine(x+sideLength/2 + 200, y+sideLength+20, x+sideLength/2, y+sideLength+20, outerPaint);
            canvas.drawLine(x+sideLength/2 + 200, y+sideLength, x+sideLength/2 + 200, y+sideLength+20, outerPaint);
        }
        else
        {
            canvas.drawLine(x+sideLength/2 - 200, y+sideLength+20, x+sideLength/2, y+sideLength+20, outerPaint);
            canvas.drawLine(x+sideLength/2 - 200, y+sideLength, x+sideLength/2 - 200, y+sideLength+20, outerPaint);

        }
    }

    private void drawCoupleChildLine(Canvas canvas, int x, int y, int sideLength) {
        canvas.drawLine(x+sideLength/2, y, x+sideLength/2, y-75+sideLength+20, outerPaint);
        canvas.drawLine(x+sideLength/2, y-75+sideLength+20, x+sideLength/2+100, y-75+sideLength+20, outerPaint);
        canvas.drawLine(x+sideLength/2, y-75+sideLength+20, x+sideLength/2-100, y-75+sideLength+20, outerPaint);
        canvas.drawLine(x+sideLength/2-100, y-75+sideLength+20, x+sideLength/2-100, y-75+sideLength, outerPaint);
        canvas.drawLine(x+sideLength/2+100, y-75+sideLength+20, x+sideLength/2+100, y-75+sideLength, outerPaint);
    }

    private void drawChildLine(Canvas canvas, int x, int y, int sideLength) {
        canvas.drawLine(x+sideLength/2, y, x+sideLength/2, y-75+sideLength, outerPaint);
    }

    private void generateGroup(GenogramObj gen, Canvas canvas, int x, int y, int sideLength) {
        LOG.debug("Gen is not null.");
        RectF outerRect = new RectF(x, y, x+sideLength, y+sideLength);
        LOG.debug("Draw outerRect "+outerRect.left +" "+outerRect.top + " "+outerRect.height()+" "+outerRect.width());

        RectF innerRect = new RectF(x + 5, y + 5, x + sideLength - 5, y + sideLength - 5);
        LOG.debug("Draw innerRect "+innerRect.left +" "+innerRect.top + " "+innerRect.height()+" "+innerRect.width());

        if (gen.getSex() != null && gen.getSex().equals(GenogramObj.SEX_MALE))
        {
            LOG.debug("Draw Male");
            canvas.drawRect(outerRect, outerPaint);
            //canvas.drawRect(innerRect, innerPaint);
        }
        else if (gen.getSex() != null)
        {
            LOG.debug("Draw Female");
            LOG.debug("Draw outerCircle "+outerRect.left +" "+outerRect.top + " "+outerRect.height()+" "+outerRect.width());
            canvas.drawCircle(outerRect.centerX(), outerRect.centerY(), sideLength / 2, outerPaint);
       }

        if (gen.getAge() != null)
        {
            String text = gen.getAge();
            drawCenterText(text, outerRect, canvas, outerPaint);
        }

        if (gen.getOccupation() != null)
        {
            String text = gen.getOccupation();
            if (gen.getSalary() != null && !gen.getSalary().equals(""))
            {
                text += " ($"+gen.getSalary()+")";
            }
            drawOccupation(text, outerRect, sideLength, canvas, outerPaint);
        }

        if (gen.getIllness() != null)
        {
            String text = gen.getIllness();
            drawIllness(text, outerRect, sideLength, canvas, outerPaint);
        }

        if (gen.getFullName() != null)
        {
            String text = gen.getFullName();
            drawName(text, outerRect, sideLength, canvas, outerPaint);
        }
        gen.setX(x);
        gen.setY(y);

    }

    private void drawIllness(String text, RectF rectF, int sideLength, Canvas canvas, Paint paint) {
        float x;
        float y;
        x = rectF.centerX() + sideLength / 2 + 5;
        y = rectF.centerY() + sideLength / 2;
        canvas.drawText(text, x, y, paint);
    }

    private void drawOccupation(String text, RectF rectF, int sideLength, Canvas canvas, Paint paint) {
        float x;
        float y;
        x = rectF.centerX() + sideLength / 2 + 5;
        y = rectF.centerY() - sideLength / 2;
        canvas.drawText(text, x, y, paint);
    }

    private void drawName(String text, RectF rectF, int sideLength, Canvas canvas, Paint paint) {
        float x;
        float y;
        x = rectF.centerX() - paint.measureText(text) - sideLength / 2 - 5;

        Paint.FontMetrics metrics = paint.getFontMetrics();
        float acent = Math.abs(metrics.ascent);
        float descent = Math.abs(metrics.descent);
        y = rectF.centerY() + (acent - descent) / 2f;
        canvas.drawText(text, x, y, paint);
    }

    public static void drawCenterText(String text, RectF rectF, Canvas canvas, Paint paint) {
        Paint.Align align = paint.getTextAlign();
        float x;
        float y;
        //x
        if (align == Paint.Align.LEFT) {
            x = rectF.centerX() - paint.measureText(text) / 2;
        } else if (align == Paint.Align.CENTER) {
            x = rectF.centerX();
        } else {
            x = rectF.centerX() + paint.measureText(text) / 2;
        }
        //y
        Paint.FontMetrics metrics = paint.getFontMetrics();
        float acent = Math.abs(metrics.ascent);
        float descent = Math.abs(metrics.descent);
        y = rectF.centerY() + (acent - descent) / 2f;
        canvas.drawText(text, x, y, paint);
    }
}

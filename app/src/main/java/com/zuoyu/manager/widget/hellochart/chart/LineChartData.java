package com.zuoyu.manager.widget.hellochart.chart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lecho.lib.hellocharts.model.AbstractChartData;
import lecho.lib.hellocharts.model.PointValue;

/**
 * Created by zhangye on 17-3-9.
 */

public class LineChartData extends AbstractChartData {
    public static final float DEFAULT_BASE_VALUE = 0.0F;
    private List<Line> lines = new ArrayList();
    private float baseValue = 0.0F;

    public LineChartData() {

    }

    public LineChartData(List<Line> lines) {
        this.setLines(lines);
    }

    public LineChartData(LineChartData data) {
        super(data);
        this.baseValue = data.baseValue;
        Iterator var2 = data.lines.iterator();

        while(var2.hasNext()) {
            Line line = (Line)var2.next();
            this.lines.add(new Line(line));
        }

    }

    public static LineChartData generateDummyData() {
        boolean numValues = true;
        LineChartData data = new LineChartData();
        ArrayList values = new ArrayList(4);
        values.add(new PointValue(0.0F, 2.0F));
        values.add(new PointValue(1.0F, 4.0F));
        values.add(new PointValue(2.0F, 3.0F));
        values.add(new PointValue(3.0F, 4.0F));
        Line line = new Line(values);
        ArrayList lines = new ArrayList(1);
        lines.add(line);
        data.setLines(lines);
        return data;
    }

    public void update(float scale) {
        Iterator var2 = this.lines.iterator();

        while(var2.hasNext()) {
            lecho.lib.hellocharts.model.Line line = (lecho.lib.hellocharts.model.Line)var2.next();
            line.update(scale);
        }

    }

    public void finish() {
        Iterator var1 = this.lines.iterator();

        while(var1.hasNext()) {
            lecho.lib.hellocharts.model.Line line = (lecho.lib.hellocharts.model.Line)var1.next();
            line.finish();
        }

    }

    public List<Line> getLines() {
        return this.lines;
    }

    public LineChartData setLines(List<Line> lines) {
        if(null == lines) {
            this.lines = new ArrayList();
        } else {
            this.lines = lines;
        }

        return this;
    }

    public float getBaseValue() {
        return this.baseValue;
    }

    public LineChartData setBaseValue(float baseValue) {
        this.baseValue = baseValue;
        return this;
    }
}

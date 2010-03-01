package solved;

import tasks.ITask;

import static java.lang.Math.*;

//Answer : 709
public class Task_099 implements ITask {

    // deleted...
    int d[][] = {
            {519432,525806},
            {632382,518061},
            {78864,613712},
            {466580,530130},
            {780495,510032},
            {525895,525320},
            {15991,714883},
            {960290,502358},
            {760018,511029},
            {166800,575487},
            {210884,564478},
            {555151,523163},
            {681146,515199},
            {563395,522587},
            {738250,512126},
            {923525,503780},
            {595148,520429},
            {177108,572629},
            {750923,511482},
            {440902,532446},
            {881418,505504},
            {422489,534197}
    };

    public void solving() {
        double mx = -1;
        int res = -1;
        for (int i = 0; i < d.length; ++i) {
            double epow = d[i][1]*log(d[i][0]);
            if (epow > mx) {
                mx = epow;
                res = i;
            }
        }

        System.out.println(res + 1);
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<process> processes = new ArrayList<process>();
        process p1 = new process(4, 8, 240, 148, 300);
        process p2 = new process(8, 124, 200, 204, 352);
        process p3 = new process(52, 56, 60, 64, 68);
        process p4 = new process(4, 4, 4, 4, 4);
        process p5 = new process(400, 400, 400, 400, 400);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        int frameSize = 400;
        Map<Integer, Integer> frames = new HashMap<>();
        frames.put(1, 0);
        frames.put(2, 0);
        frames.put(3, 0);
        frames.put(4, 0);
        frames.put(5, 0);
        frames.put(6, 0);
        frames.put(7, 0);
        frames.put(8, 0);
        frames.put(9, 0);
        frames.put(10, 0);
        for (process p : processes) {
            int v1f = -1, v2f = -1, v3f = -1, v4f = -1, v5f = -1;
            int v1o = -1, v2o = -1, v3o = -1, v4o = -1, v5o = -1;
            for (int vSize : p.varSizeList) {
                if (vSize < frameSize - frames.get(1)) {
                    v1f = 1;
                    v1o = frameSize - frames.get(1);
                    frames.put(1, (frames.get(1) + vSize));
                } else if (vSize < frameSize - frames.get(2)) {
                    v1f = 2;
                    v1o = frameSize - frames.get(2);
                    frames.put(2, (frames.get(2) + vSize));
                } else if (vSize < frameSize - frames.get(3)) {
                    v1f = 3;
                    v1o = frameSize - frames.get(3);
                    frames.put(3, (frames.get(3) + vSize));
                } else if (vSize < frameSize - frames.get(4)) {
                    v1f = 4;
                    v1o = frameSize - frames.get(4);
                    frames.put(4, (frames.get(4) + vSize));
                } else if (vSize < frameSize - frames.get(5)) {
                    v1f = 5;
                    v1o = frameSize - frames.get(5);
                    frames.put(5, (frames.get(5) + vSize));
                } else if (vSize < frameSize - frames.get(5)) {
                    v1f = 6;
                    v1o = frameSize - frames.get(6);
                    frames.put(6, (frames.get(6) + vSize));
                } else if (vSize < frameSize - frames.get(6)) {
                    v1f = 7;
                    v1o = frameSize - frames.get(7);
                    frames.put(7, (frames.get(7) + vSize));
                } else if (vSize < frameSize - frames.get(7)) {
                    v1f = 8;
                    v1o = frameSize - frames.get(8);
                    frames.put(8, (frames.get(8) + vSize));
                } else if (vSize < frameSize - frames.get(8)) {
                    v1f = 9;
                    v1o = frameSize - frames.get(9);
                    frames.put(9, (frames.get(9) + vSize));
                } else if (vSize < frameSize - frames.get(9)) {
                    v1f = 10;
                    v1o = frameSize - frames.get(10);
                    frames.put(10, (frames.get(10) + vSize));
                }
            }
            p.setFrames(v1f, v2f, v3f, v4f, v5f);
            p.setOffsets(v1o, v2o, v3o, v4o, v5o);
        }
    }
}

class process {
    int var1Size;
    int var2Size;
    int var3Size;
    int var4Size;
    int var5Size;
    int var1Frame;
    int var2Frame;
    int var3Frame;
    int var4Frame;
    int var5Frame;
    int var1Offset;
    int var2Offset;
    int var3Offset;
    int var4Offset;
    int var5Offset;
    ArrayList<Integer> varSizeList = new ArrayList<>();
    ArrayList<Integer> varFrameList = new ArrayList<>();

    public void setFrames(int var1Frame, int var2Frame, int var3Frame, int var4Frame, int var5Frame) {
        setVar1Frame(var1Frame);
        setVar2Frame(var2Frame);
        setVar3Frame(var3Frame);
        setVar4Frame(var4Frame);
        setVar5Frame(var5Frame);
    }

    public void setOffsets(int var1Offset, int var2Offset, int var3Offset, int var4Offset, int var5Offset) {
        setVar1Offset(var1Offset);
        setVar2Offset(var2Offset);
        setVar3Offset(var3Offset);
        setVar4Offset(var4Offset);
        setVar5Offset(var5Offset);
    }

    public void setVar1Offset(int var1Offset) {
        this.var1Offset = var1Offset;
    }

    public void setVar2Offset(int var2Offset) {
        this.var2Offset = var2Offset;
    }

    public void setVar3Offset(int var3Offset) {
        this.var3Offset = var3Offset;
    }

    public void setVar4Offset(int var4Offset) {
        this.var4Offset = var4Offset;
    }

    public void setVar5Offset(int var5Offset) {
        this.var5Offset = var5Offset;
    }

    public void setVar1Frame(int var1Frame) {
        this.var1Frame = var1Frame;
    }

    public void setVar2Frame(int var2Frame) {
        this.var2Frame = var2Frame;
    }

    public void setVar3Frame(int var3Frame) {
        this.var3Frame = var3Frame;
    }

    public void setVar4Frame(int var4Frame) {
        this.var4Frame = var4Frame;
    }

    public void setVar5Frame(int var5Frame) {
        this.var5Frame = var5Frame;
    }

    public ArrayList<Integer> getVarSizeList() {
        varSizeList.add(var1Size);
        varSizeList.add(var2Size);
        varSizeList.add(var3Size);
        varSizeList.add(var4Size);
        varSizeList.add(var5Size);
        return varSizeList;
    }

    public ArrayList<Integer> getVarFrameList() {
        varFrameList.add(var1Frame);
        varFrameList.add(var2Frame);
        varFrameList.add(var3Frame);
        varFrameList.add(var4Frame);
        varFrameList.add(var5Frame);
        return varFrameList;
    }

    public process(int var1Size, int var2Size, int var3Size, int var4Size, int var5Size) {
        this.var1Size = var1Size;
        this.var2Size = var2Size;
        this.var3Size = var3Size;
        this.var4Size = var4Size;
        this.var5Size = var5Size;
    }

    int relativeAddress(int var) {
        return switch (var) {
            case 1 -> +1;
            case 2 -> var1Size + 1;
            case 3 -> var2Size + var1Size + 1;
            case 4 -> var3Size + var2Size + var1Size + 1;
            case 5 -> var4Size + var3Size + var2Size + var1Size + 1;
            default -> -1;
        };
    }
}
import java.util.*;

import static java.lang.Thread.sleep;

public class Main {
    static int frameSize = 400;
    static Map<Integer, Integer> pages = new HashMap<>();
    static Map<Integer, Integer> frames = new HashMap<>();
    static ArrayList<process> processes = new ArrayList<>();
    static int[] History = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static void main(String[] args) throws InterruptedException {
        process p1 = new process("P1", 4, 8, 240, 148, 300, 4, 8, 240, 148, 300);
        process p2 = new process("P2", 8, 124, 200, 204, 352, 8, 124, 200, 204, 352);
        process p3 = new process("P3", 52, 56, 60, 64, 68, 52, 56, 60, 64, 68);
        process p4 = new process("P4", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
        process p5 = new process("P5", 400, 400, 400, 400, 400, 400, 400, 400, 400, 400);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        for (int i = 1; i <= 10; i++) {
            pages.put(i, 0);
        }
        framing();


        while (true) {
            System.out.print("Enter Process Number:");
            Scanner input = new Scanner(System.in);
            int processNumber = input.nextInt();
            if (processNumber == 0) break;
            System.out.print("Enter Variable Number:");
            int VariableNumber = input.nextInt();
            Paging(processNumber, VariableNumber);
        }
    }

    private static void framing() {
        for (int i = 1; i <= 50; i++) {
            frames.put(i, 0);
        }
        for (process p : processes) {
            for (int vSize : p.getVarSizeList()) {
                int variableIndex = p.getVarSizeList().indexOf(vSize);
                for (int i = 1; i <= 50; i++) {
                    if (vSize <= (frameSize - frames.get(i)) && p.getVarFrameList().get(variableIndex) == -1) {
                        p.setVarFrame(variableIndex + 1, i);
                        p.setVarOffset(variableIndex + 1, frames.get(i) + 1);
                        frames.put(i, (frames.get(i) + vSize));
                        break;
                    }
                }
            }
        }
    }

    private static void Paging(int processNumber, int variableNumber) throws InterruptedException {
        process p = processes.get(processNumber - 1);
        if (p.getVarPageList().get(variableNumber - 1) == -1) {
            System.out.println("Page Fault!\nWait for Paging...");
            sleep(2000);
            int replacePageIndex = indexOfSmallest(History);
            for (process pp : processes) {
                Collections.replaceAll(pp.getVarPageList(), replacePageIndex, -1);
                for (int ppVariableFrameNumber : pp.getVarFrameList()) {
                    int VariableIndex = pp.getVarFrameList().indexOf(ppVariableFrameNumber);
                    if (ppVariableFrameNumber == p.getVarFrameList().get(variableNumber - 1)) {
                        pp.setVarPage(VariableIndex + 1, replacePageIndex);
                    }
                }
            }
            p.setVarPage(variableNumber, replacePageIndex);
            History[replacePageIndex]++;
            System.out.println("Name: " + p.name +
                    "\nRelative Address: " + p.getRelativeAddress(variableNumber) +
                    "\nLogical Address: Page " + p.getVarPageList().get(variableNumber - 1) + "\t\t\toffset " + p.getVarOffsetList().get(variableNumber - 1) +
                    "\nPhysical Address: Frame " + p.getVarFrameList().get(variableNumber - 1) + "\t\t\tOffset " + p.getVarOffsetList().get(variableNumber - 1) +
                    "\n0b" + Integer.toBinaryString((p.getVarFrameList().get(variableNumber - 1) * 400 + p.getVarOffsetList().get(variableNumber - 1))) + " = " + (p.getVarFrameList().get(variableNumber - 1) * 400 + p.getVarOffsetList().get(variableNumber - 1)) +
                    "\n------------------------------------------------------------------------");
        } else {
            System.out.println("Name: " + p.name +
                    "\nRelative Address: " + p.getRelativeAddress(variableNumber) +
                    "\nLogical Address: Page " + p.getVarPageList().get(variableNumber - 1) + "\t\t\toffset " + p.getVarOffsetList().get(variableNumber - 1) +
                    "\nPhysical Address: Frame " + p.getVarFrameList().get(variableNumber - 1) + "\t\t\tOffset " + p.getVarOffsetList().get(variableNumber - 1) +
                    "\n------------------------------------------------------------------------");
            History[p.getVarSizeList().indexOf(p.getVarSizeList().get(variableNumber - 1))]++;
            History[p.getVarSizeList().indexOf(p.getVarSizeList().get(variableNumber - 1))]++;
        }
    }

    public static int indexOfSmallest(int[] array) {
        int index = 0;
        int min = array[index];

        for (int i = 1; i < array.length; i++) {
            if (array[i] <= min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }
}

class process {
    String name;
    int var1Size;
    int var2Size;
    int var3Size;
    int var4Size;
    int var5Size;
    int var6Size;
    int var7Size;
    int var8Size;
    int var9Size;
    int var10Size;

    int var1Frame = -1;
    int var2Frame = -1;
    int var3Frame = -1;
    int var4Frame = -1;
    int var5Frame = -1;
    int var6Frame = -1;
    int var7Frame = -1;
    int var8Frame = -1;
    int var9Frame = -1;
    int var10Frame = -1;

    int var1Offset = -1;
    int var2Offset = -1;
    int var3Offset = -1;
    int var4Offset = -1;
    int var5Offset = -1;
    int var6Offset = -1;
    int var7Offset = -1;
    int var8Offset = -1;
    int var9Offset = -1;
    int var10Offset = -1;


    int var1Page = -1;
    int var2Page = -1;
    int var3Page = -1;
    int var4Page = -1;
    int var5Page = -1;
    int var6Page = -1;
    int var7Page = -1;
    int var8Page = -1;
    int var9Page = -1;
    int var10Page = -1;
    ArrayList<Integer> varSizeList;
    ArrayList<Integer> varFrameList;
    ArrayList<Integer> varPageList;
    ArrayList<Integer> varOffsetList;


    public void setVar1Page(int var1Page) {
        this.var1Page = var1Page;
    }

    public void setVar2Page(int var2Page) {
        this.var2Page = var2Page;
    }

    public void setVar3Page(int var3Page) {
        this.var3Page = var3Page;
    }

    public void setVar4Page(int var4Page) {
        this.var4Page = var4Page;
    }

    public void setVar5Page(int var5Page) {
        this.var5Page = var5Page;
    }

    public void setVar6Page(int var6Page) {
        this.var6Page = var6Page;
    }

    public void setVar7Page(int var7Page) {
        this.var7Page = var7Page;
    }

    public void setVar8Page(int var8Page) {
        this.var8Page = var8Page;
    }

    public void setVar9Page(int var9Page) {
        this.var9Page = var9Page;
    }

    public void setVar10Page(int var10Page) {
        this.var10Page = var10Page;
    }

    public void setVar6Offset(int var6Offset) {
        this.var6Offset = var6Offset;
    }

    public void setVar7Offset(int var7Offset) {
        this.var7Offset = var7Offset;
    }

    public void setVar8Offset(int var8Offset) {
        this.var8Offset = var8Offset;
    }

    public void setVar9Offset(int var9Offset) {
        this.var9Offset = var9Offset;
    }

    public void setVar10Offset(int var10Offset) {
        this.var10Offset = var10Offset;
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


    public ArrayList<Integer> getVarSizeList() {
        return varSizeList;
    }

    public ArrayList<Integer> getVarFrameList() {
        varFrameList = new ArrayList<>();
        varFrameList.add(var1Frame);
        varFrameList.add(var2Frame);
        varFrameList.add(var3Frame);
        varFrameList.add(var4Frame);
        varFrameList.add(var5Frame);
        varFrameList.add(var6Frame);
        varFrameList.add(var7Frame);
        varFrameList.add(var8Frame);
        varFrameList.add(var9Frame);
        varFrameList.add(var10Frame);
        return varFrameList;
    }

    public ArrayList<Integer> getVarPageList() {
        varPageList = new ArrayList<>();
        varPageList.add(var1Page);
        varPageList.add(var2Page);
        varPageList.add(var3Page);
        varPageList.add(var4Page);
        varPageList.add(var5Page);
        varPageList.add(var6Page);
        varPageList.add(var7Page);
        varPageList.add(var8Page);
        varPageList.add(var9Page);
        varPageList.add(var10Page);
        return varPageList;
    }

    public ArrayList<Integer> getVarOffsetList() {
        varOffsetList = new ArrayList<>();
        varOffsetList.add(var1Offset);
        varOffsetList.add(var2Offset);
        varOffsetList.add(var3Offset);
        varOffsetList.add(var4Offset);
        varOffsetList.add(var5Offset);
        varOffsetList.add(var6Offset);
        varOffsetList.add(var7Offset);
        varOffsetList.add(var8Offset);
        varOffsetList.add(var9Offset);
        varOffsetList.add(var10Offset);
        return varOffsetList;
    }

    public process(String name, int var1Size, int var2Size, int var3Size, int var4Size, int var5Size, int var6Size, int var7Size, int var8Size, int var9Size, int var10Size) {
        this.var1Size = var1Size;
        this.var2Size = var2Size;
        this.var3Size = var3Size;
        this.var4Size = var4Size;
        this.var5Size = var5Size;
        this.var6Size = var6Size;
        this.var7Size = var7Size;
        this.var8Size = var8Size;
        this.var9Size = var9Size;
        this.var10Size = var10Size;
        this.name = name;
        varSizeList = new ArrayList<>();
        varSizeList.add(var1Size);
        varSizeList.add(var2Size);
        varSizeList.add(var3Size);
        varSizeList.add(var4Size);
        varSizeList.add(var5Size);
        varSizeList.add(var6Size);
        varSizeList.add(var7Size);
        varSizeList.add(var8Size);
        varSizeList.add(var9Size);
        varSizeList.add(var10Size);
    }


    int getRelativeAddress(int var) {
        return switch (var) {
            case 1 -> 1;
            case 2 -> var1Size + 1;
            case 3 -> var2Size + var1Size + 1;
            case 4 -> var3Size + var2Size + var1Size + 1;
            case 5 -> var4Size + var3Size + var2Size + var1Size + 1;
            case 6 -> var5Size + var4Size + var3Size + var2Size + var1Size + 1;
            case 7 -> var6Size + var5Size + var4Size + var3Size + var2Size + var1Size + 1;
            case 8 -> var7Size + var6Size + var5Size + var4Size + var3Size + var2Size + var1Size + 1;
            case 9 -> var8Size + var7Size + var6Size + var5Size + var4Size + var3Size + var2Size + var1Size + 1;
            case 10 ->
                    var9Size + var8Size + var7Size + var6Size + var5Size + var4Size + var3Size + var2Size + var1Size + 1;

            default -> -1;
        };
    }


    public void setVarPage(int Variable, int page) {
        switch (Variable) {
            case 1 -> setVar1Page(page);
            case 2 -> setVar2Page(page);
            case 3 -> setVar3Page(page);
            case 4 -> setVar4Page(page);
            case 5 -> setVar5Page(page);
            case 6 -> setVar6Page(page);
            case 7 -> setVar7Page(page);
            case 8 -> setVar8Page(page);
            case 9 -> setVar9Page(page);
            case 10 -> setVar10Page(page);
        }
    }

    public void setVarFrame(int Variable, int frame) {
        switch (Variable) {
            case 1 -> setVar1Frame(frame);
            case 2 -> setVar2Frame(frame);
            case 3 -> setVar3Frame(frame);
            case 4 -> setVar4Frame(frame);
            case 5 -> setVar5Frame(frame);
            case 6 -> setVar6Frame(frame);
            case 7 -> setVar7Frame(frame);
            case 8 -> setVar8Frame(frame);
            case 9 -> setVar9Frame(frame);
            case 10 -> setVar10Frame(frame);

        }
    }

    public void setVarOffset(int Variable, int offset) {
        switch (Variable) {
            case 1 -> setVar1Offset(offset);
            case 2 -> setVar2Offset(offset);
            case 3 -> setVar3Offset(offset);
            case 4 -> setVar4Offset(offset);
            case 5 -> setVar5Offset(offset);
            case 6 -> setVar6Offset(offset);
            case 7 -> setVar7Offset(offset);
            case 8 -> setVar8Offset(offset);
            case 9 -> setVar9Offset(offset);
            case 10 -> setVar10Offset(offset);

        }
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

    public void setVar6Frame(int var6Frame) {
        this.var6Frame = var6Frame;
    }

    public void setVar7Frame(int var7Frame) {
        this.var7Frame = var7Frame;
    }

    public void setVar8Frame(int var8Frame) {
        this.var8Frame = var8Frame;
    }

    public void setVar9Frame(int var9Frame) {
        this.var9Frame = var9Frame;
    }

    public void setVar10Frame(int var10Frame) {
        this.var10Frame = var10Frame;
    }
}

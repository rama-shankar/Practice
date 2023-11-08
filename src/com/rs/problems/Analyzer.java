package com.rs.problems;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Analyzer {
    private Map<Integer, String> strings = new HashMap<>();
    private Map<Integer, Integer> stringRefs = new HashMap<>();
    private Map<Integer, Integer> classRefs = new HashMap<>();
    private Map<Integer, int[]> methodRefs = new HashMap<>();

    private int idForString(String string) {
        for (Entry<Integer, Integer> ref : stringRefs.entrySet()) {
            if (strings.get(ref.getValue()) == null) continue;
            if (strings.get(ref.getValue()).equals(string)) {
                return ref.getKey();
            }
        }
        for (Entry<Integer, String> str : strings.entrySet()) {
            if (str.getValue().equals(string)) {
                return str.getKey();
            }
        }
        throw new AssertionError();
    }

    private String nameForMajorVersion(int version) {
        switch (version) {
            case 0x33:
                return "J2SE 7";
            case 0x32:
                return "J2SE 6.0";
            case 0x31:
                return "J2SE 5.0";
            case 0x30:
                return "JDK 1.4";
            case 0x2F:
                return "JDK 1.3";
            case 0x2E:
                return "JDK 1.2";
            case 0x2D:
                return "JDK 1.1";
        }
        return "";
    }

    private String className(int id) {
        Integer id2 = classRefs.get(id);
        if (id2 == null) return "";
        if (stringRefs.containsKey(id2)) {
            id2 = stringRefs.get(id2);
        }
        return strings.get(id2);
    }

    private String stringForId(int id) {
        if (stringRefs.containsKey(id)) {
            id = stringRefs.get(id);
        }
        return strings.get(id);
    }

    public static void main(String[] args) throws IOException {
       // if (args.length != 1) throw new AssertionError("Unexpected arguments length. Use as: <file>");
        new Analyzer(new File("C:\\Users\\earahms\\Practice\\out\\production\\Practice\\com\\rs\\problems\\Two_sum.class"));
    }

    private final DataInputStream in;

    public Analyzer(File file) throws IOException {
        in = new DataInputStream(new FileInputStream(file));
        analyze();
    }

    private void analyze() throws IOException {
        int magicNumber = in.readInt();
        if (magicNumber != 0xCAFEBABE) throw new AssertionError();

        short minorVersion = in.readShort();
        short majorVersion = in.readShort();

        System.out.println("Version: Major=" + Short.toString(majorVersion) + " (" + nameForMajorVersion(majorVersion) + "); Minor=" + Short.toString(minorVersion));

        System.out.println();

        short constantPoolCount = in.readShort();
        System.out.println("Constant Pool, " + Short.toString(constantPoolCount) + " entries:");
        readConstantPool(constantPoolCount);

        System.out.println();

        short accessFlags = in.readShort();
        System.out.println("Access Flags: " + Integer.toBinaryString(accessFlags & 0xFFFF));

        System.out.println();

        short thisClassId = in.readShort();
        short superClassId = in.readShort();
        System.out.println("Class CP ID: " + Short.toString(thisClassId));
        System.out.println("Superclass CP ID: " + Short.toString(superClassId));

        System.out.println();

        short interfaceCount = in.readShort();
        readInterfaces(interfaceCount);

        short fieldCount = in.readShort();
        readFields(fieldCount);

        short methodCount = in.readShort();
        readMethods(methodCount);

        short attributeCount = in.readShort();
        readAttributes(attributeCount);
    }

    private void readInterfaces(short interfaceCount) throws IOException {
        for (short index = 0; index < interfaceCount; index++) {
            readClassInfo();
            System.out.println();
        }
    }

    private void readClassInfo() throws IOException {
        byte tag = in.readByte();
        short nameIndex = in.readShort();
        System.out.println("INTERFACE");
        System.out.println("	TAG: " + tag);
        System.out.println("	" + className(nameIndex));
    }

    private void readFields(short fieldCount) throws IOException {
        for (short index = 0; index < fieldCount; index++) {
            readField();
            System.out.println();
        }
    }

    private void readField() throws IOException {
        short accessFlags = in.readShort();
        short nameCPIndex = in.readShort();
        short descriptorCPIndex = in.readShort();

        System.out.println("FIELD:");
        System.out.println("	Access Flags: " + Integer.toBinaryString(accessFlags & 0xFFFF));

        System.out.println("	Name CP Index: " + Short.toString(nameCPIndex));
        System.out.println("	Desc CP Index: " + Short.toString(descriptorCPIndex));

        short attributes = in.readShort();
        for (int i = 0; i < attributes; i++) {
            readAttribute();
        }
    }

    private void readMethods(short methodCount) throws IOException {
        for (short index = 0; index < methodCount; index++) {
            readMethod();
            System.out.println();
        }
    }

    private void readMethod() throws IOException {
        short accessFlags = in.readShort();
        short nameCPIndex = in.readShort();
        short descriptorCPIndex = in.readShort();

        System.out.println("METHOD:");
        System.out.println("	" + stringForId(nameCPIndex));
        System.out.println("	Access Flags: " + Integer.toBinaryString(accessFlags & 0xFFFF));

        System.out.println("	Name CP Index: " + Short.toString(nameCPIndex));
        System.out.println("	Desc CP Index: " + Short.toString(descriptorCPIndex));

        short attributesCount = in.readShort();

        for (short index = 0; index < attributesCount; index++) {
            readAttribute();
        }
    }

    private void readAttribute() throws IOException {
        short type = in.readShort();
        int length = in.readInt();

        if (type == idForString("Code")) {
            readCodeAttribute(length);
        } else {
            readUnknownAttribute(length);
        }
    }

    private void readUnknownAttribute(int length) throws IOException {
        byte[] data = new byte[length];
        in.readFully(data);
    }

    private void readCodeAttribute(int length) throws IOException {
        short maxStack = in.readShort();
        short maxLocals = in.readShort();

        System.out.println("	Max Stack Size: " + Short.toString(maxStack));
        System.out.println("	Max Local Vars: " + Short.toString(maxLocals));

        System.out.println("	Bytecode:");
        int codeLength = in.readInt();
        int index = 0;
        while (index < codeLength) {
            System.out.print("		" + Integer.toString(index) + ": ");
            index += readInstruction();
            System.out.println();
        }

        short exceptionLength = in.readShort();
        for (int ex = 0; ex < exceptionLength; ex++) {
            short startPc = in.readShort();
            short endPc = in.readShort();
            short handlerPc = in.readShort();
            short catchType = in.readShort();

            System.out.println("	Exception: " + className(catchType) + "; SPC: " + Short.toString(startPc) + ", EPC: " + Short.toString(endPc) + ", HPC: " + Short.toString(handlerPc));
        }

        short attributes = in.readShort();
        for (int i = 0; i < attributes; i++) {
            readAttribute();
        }
    }

    private int readInstruction() throws IOException {
        byte op = in.readByte();

        switch (op) {
            case (byte) 0x32:
                System.out.print("aaload");
                return 1;
            case (byte) 0x53:
                System.out.print("aastore");
                return 1;
            case (byte) 0x01:
                System.out.print("aconst_null");
                return 1;
            case (byte) 0x19:
                System.out.print("aload");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x2a:
                System.out.print("aload_0");
                return 1;
            case (byte) 0x2b:
                System.out.print("aload_1");
                return 1;
            case (byte) 0x2c:
                System.out.print("aload_2");
                return 1;
            case (byte) 0x2d:
                System.out.print("aload_3");
                return 1;
            case (byte) 0xbd:
                System.out.print("anewarray");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xb0:
                System.out.print("areturn");
                return 1;
            case (byte) 0xbe:
                System.out.print("arraylength");
                return 1;
            case (byte) 0x3a:
                System.out.print("astore");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x4b:
                System.out.print("astore_0");
                return 1;
            case (byte) 0x4c:
                System.out.print("astore_1");
                return 1;
            case (byte) 0x4d:
                System.out.print("astore_2");
                return 1;
            case (byte) 0x4e:
                System.out.print("astore_3");
                return 1;
            case (byte) 0xbf:
                System.out.print("athrow");
                return 1;
            case (byte) 0x33:
                System.out.print("baload");
                return 1;
            case (byte) 0x54:
                System.out.print("bastore");
                return 1;
            case (byte) 0x10:
                System.out.print("bipush");
                System.out.print(", i=" + in.readByte());
                return 2;
            case (byte) 0x34:
                System.out.print("caload");
                return 1;
            case (byte) 0x55:
                System.out.print("castore");
                return 1;
            case (byte) 0xc0:
                System.out.print("checkcast");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x63:
                System.out.print("dadd");
                return 1;
            case (byte) 0x31:
                System.out.print("daload");
                return 1;
            case (byte) 0x52:
                System.out.print("dastore");
                return 1;
            case (byte) 0x98:
                System.out.print("dcmpg");
                return 1;
            case (byte) 0x97:
                System.out.print("dcmpl");
                return 1;
            case (byte) 0x0e:
                System.out.print("dconst_0");
                return 1;
            case (byte) 0x0f:
                System.out.print("dconst_1");
                return 1;
            case (byte) 0x6f:
                System.out.print("ddiv");
                return 1;
            case (byte) 0x18:
                System.out.print("dload");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x26:
                System.out.print("dload_0");
                return 1;
            case (byte) 0x27:
                System.out.print("dload_1");
                return 1;
            case (byte) 0x28:
                System.out.print("dload_2");
                return 1;
            case (byte) 0x29:
                System.out.print("dload_3");
                return 1;
            case (byte) 0x6b:
                System.out.print("dmul");
                return 1;
            case (byte) 0x77:
                System.out.print("dneg");
                return 1;
            case (byte) 0x73:
                System.out.print("drem");
                return 1;
            case (byte) 0xaf:
                System.out.print("dreturn");
                return 1;
            case (byte) 0x39:
                System.out.print("dstore");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x47:
                System.out.print("dstore_0");
                return 1;
            case (byte) 0x48:
                System.out.print("dstore_1");
                return 1;
            case (byte) 0x49:
                System.out.print("dstore_2");
                return 1;
            case (byte) 0x4a:
                System.out.print("dstore_3");
                return 1;
            case (byte) 0x67:
                System.out.print("dsub");
                return 1;
            case (byte) 0x59:
                System.out.print("dup");
                return 1;
            case (byte) 0x5a:
                System.out.print("dup_x1");
                return 1;
            case (byte) 0x5b:
                System.out.print("dup_x2");
                return 1;
            case (byte) 0x5c:
                System.out.print("dup2");
                return 1;
            case (byte) 0x5d:
                System.out.print("dup2_x1");
                return 1;
            case (byte) 0x5e:
                System.out.print("dup2_x2");
                return 1;
            case (byte) 0x62:
                System.out.print("fadd");
                return 1;
            case (byte) 0x30:
                System.out.print("faload");
                return 1;
            case (byte) 0x51:
                System.out.print("fastore");
                return 1;
            case (byte) 0x96:
                System.out.print("fcmpg");
                return 1;
            case (byte) 0x95:
                System.out.print("fcmpl");
                return 1;
            case (byte) 0x0b:
                System.out.print("fconst_0");
                return 1;
            case (byte) 0x0c:
                System.out.print("fconst_1");
                return 1;
            case (byte) 0x0d:
                System.out.print("fconst_2");
                return 1;
            case (byte) 0x6e:
                System.out.print("fdiv");
                return 1;
            case (byte) 0x17:
                System.out.print("fload");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x22:
                System.out.print("fload_0");
                return 1;
            case (byte) 0x23:
                System.out.print("fload_1");
                return 1;
            case (byte) 0x24:
                System.out.print("fload_2");
                return 1;
            case (byte) 0x25:
                System.out.print("fload_3");
                return 1;
            case (byte) 0x6a:
                System.out.print("fmul");
                return 1;
            case (byte) 0x76:
                System.out.print("fneg");
                return 1;
            case (byte) 0x72:
                System.out.print("frem");
                return 1;
            case (byte) 0xae:
                System.out.print("freturn");
                return 1;
            case (byte) 0x38:
                System.out.print("fstore");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x43:
                System.out.print("fstore_0");
                return 1;
            case (byte) 0x44:
                System.out.print("fstore_1");
                return 1;
            case (byte) 0x45:
                System.out.print("fstore_2");
                return 1;
            case (byte) 0x46:
                System.out.print("fstore_3");
                return 1;
            case (byte) 0x66:
                System.out.print("fsub");
                return 1;
            case (byte) 0xb4:
                System.out.print("getfield");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xb2:
                System.out.print("getstatic");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xa7:
                System.out.print("goto");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xc8:
                System.out.print("goto_w");
                System.out.print(", index=" + in.readInt());
                return 5;
            case (byte) 0x85:
                System.out.print("i2l");
                return 1;
            case (byte) 0x86:
                System.out.print("i2f");
                return 1;
            case (byte) 0x87:
                System.out.print("i2d");
                return 1;
            case (byte) 0x88:
                System.out.print("l2i");
                return 1;
            case (byte) 0x89:
                System.out.print("l2f");
                return 1;
            case (byte) 0x8a:
                System.out.print("l2d");
                return 1;
            case (byte) 0x8b:
                System.out.print("f2i");
                return 1;
            case (byte) 0x8c:
                System.out.print("f2l");
                return 1;
            case (byte) 0x8d:
                System.out.print("f2d");
                return 1;
            case (byte) 0x8e:
                System.out.print("d2i");
                return 1;
            case (byte) 0x8f:
                System.out.print("d2l");
                return 1;
            case (byte) 0x90:
                System.out.print("d2f");
                return 1;
            case (byte) 0x91:
                System.out.print("i2b");
                return 1;
            case (byte) 0x92:
                System.out.print("i2c");
                return 1;
            case (byte) 0x93:
                System.out.print("i2s");
                return 1;
            case (byte) 0x60:
                System.out.print("iadd");
                return 1;
            case (byte) 0x2e:
                System.out.print("iaload");
                return 1;
            case (byte) 0x7e:
                System.out.print("iand");
                return 1;
            case (byte) 0x4f:
                System.out.print("iastore");
                return 1;
            case (byte) 0x02:
                System.out.print("iconst_m1");
                return 1;
            case (byte) 0x03:
                System.out.print("iconst_0");
                return 1;
            case (byte) 0x04:
                System.out.print("iconst_1");
                return 1;
            case (byte) 0x05:
                System.out.print("iconst_2");
                return 1;
            case (byte) 0x06:
                System.out.print("iconst_3");
                return 1;
            case (byte) 0x07:
                System.out.print("iconst_4");
                return 1;
            case (byte) 0x08:
                System.out.print("iconst_5");
                return 1;
            case (byte) 0x6c:
                System.out.print("idiv");
                return 1;
            case (byte) 0xa5:
                System.out.print("if_acmpeq");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xa6:
                System.out.print("if_acmpne");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x9f:
                System.out.print("if_icmpeq");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xa0:
                System.out.print("if_icmpne");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xa1:
                System.out.print("if_icmplt");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xa2:
                System.out.print("if_icmpge");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xa3:
                System.out.print("if_icmpgt");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xa4:
                System.out.print("if_icmple");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x99:
                System.out.print("ifeq");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x9a:
                System.out.print("ifne");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x9b:
                System.out.print("iflt");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x9c:
                System.out.print("ifge");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x9d:
                System.out.print("ifgt");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x9e:
                System.out.print("ifle");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xc7:
                System.out.print("ifnonnull");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xc6:
                System.out.print("ifnull");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x84:
                System.out.print("iinc");
                System.out.print(", index=" + in.readByte() + " const=" + in.readByte());
                return 3;
            case (byte) 0x15:
                System.out.print("iload");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x1a:
                System.out.print("iload_0");
                return 1;
            case (byte) 0x1b:
                System.out.print("iload_1");
                return 1;
            case (byte) 0x1c:
                System.out.print("iload_2");
                return 1;
            case (byte) 0x1d:
                System.out.print("iload_3");
                return 1;
            case (byte) 0x68:
                System.out.print("imul");
                return 1;
            case (byte) 0x74:
                System.out.print("ineg");
                return 1;
            case (byte) 0xc1:
                System.out.print("instanceof");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xba:
                System.out.print("invokedynamic");
                System.out.print(", index=" + in.readShort() + " extra=" + in.readShort());
                return 5;
            case (byte) 0xb9:
                System.out.print("invokeinterface");
                System.out.print(", index=" + in.readShort() + " count=" + in.readByte() + " extra=" + in.readByte());
                return 5;
            case (byte) 0xb7:
                System.out.print("invokespecial");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xb8:
                System.out.print("invokestatic");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xb6:
                System.out.print("invokevirtual");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x80:
                System.out.print("ior");
                return 1;
            case (byte) 0x70:
                System.out.print("irem");
                return 1;
            case (byte) 0xac:
                System.out.print("ireturn");
                return 1;
            case (byte) 0x78:
                System.out.print("ishl");
                return 1;
            case (byte) 0x7a:
                System.out.print("ishr");
                return 1;
            case (byte) 0x36:
                System.out.print("istore");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x3b:
                System.out.print("istore_0");
                return 1;
            case (byte) 0x3c:
                System.out.print("istore_1");
                return 1;
            case (byte) 0x3d:
                System.out.print("istore_2");
                return 1;
            case (byte) 0x3e:
                System.out.print("istore_3");
                return 1;
            case (byte) 0x64:
                System.out.print("isub");
                return 1;
            case (byte) 0x7c:
                System.out.print("iushr");
                return 1;
            case (byte) 0x82:
                System.out.print("ixor");
                return 1;
            case (byte) 0xa8:
                System.out.print("jsr");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xc9:
                System.out.print("jsr_w");
                System.out.print(", index=" + in.readInt());
                return 5;
            case (byte) 0x61:
                System.out.print("ladd");
                return 1;
            case (byte) 0x2f:
                System.out.print("laload");
                return 1;
            case (byte) 0x7f:
                System.out.print("land");
                return 1;
            case (byte) 0x50:
                System.out.print("lastore");
                return 1;
            case (byte) 0x94:
                System.out.print("lcmp");
                return 1;
            case (byte) 0x09:
                System.out.print("lconst_0");
                return 1;
            case (byte) 0x0a:
                System.out.print("lconst_1");
                return 1;
            case (byte) 0x12:
                System.out.print("ldc");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x13:
                System.out.print("ldc_w");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x14:
                System.out.print("ldc2_w");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x6d:
                System.out.print("ldiv");
                return 1;
            case (byte) 0x16:
                System.out.print("lload");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x1e:
                System.out.print("lload_0");
                return 1;
            case (byte) 0x1f:
                System.out.print("lload_1");
                return 1;
            case (byte) 0x20:
                System.out.print("lload_2");
                return 1;
            case (byte) 0x21:
                System.out.print("lload_3");
                return 1;
            case (byte) 0x69:
                System.out.print("lmul");
                return 1;
            case (byte) 0x75:
                System.out.print("lneg");
                return 1;
            case (byte) 0xab:
                System.out.print("lookupswitch");
                throw new AssertionError();
            case (byte) 0x81:
                System.out.print("lor");
                return 1;
            case (byte) 0x71:
                System.out.print("lrem");
                return 1;
            case (byte) 0xad:
                System.out.print("lreturn");
                return 1;
            case (byte) 0x79:
                System.out.print("lshl");
                return 1;
            case (byte) 0x7b:
                System.out.print("lshr");
                return 1;
            case (byte) 0x37:
                System.out.print("lstore");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0x3f:
                System.out.print("lstore_0");
                return 1;
            case (byte) 0x40:
                System.out.print("lstore_1");
                return 1;
            case (byte) 0x41:
                System.out.print("lstore_2");
                return 1;
            case (byte) 0x42:
                System.out.print("lstore_3");
                return 1;
            case (byte) 0x65:
                System.out.print("lsub");
                return 1;
            case (byte) 0x7d:
                System.out.print("lushr");
                return 1;
            case (byte) 0x83:
                System.out.print("lxor");
                return 1;
            case (byte) 0xc2:
                System.out.print("monitorenter");
                return 1;
            case (byte) 0xc3:
                System.out.print("monitorexit");
                return 1;
            case (byte) 0xc5:
                System.out.print("multianewarray");
                System.out.print(", index=" + in.readShort() + " dimensions=" + in.readByte());
                return 4;
            case (byte) 0xbb:
                System.out.print("new");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xbc:
                System.out.print("newarray");
                System.out.print(", type=" + in.readByte());
                return 2;
            case (byte) 0x00:
                System.out.print("nop");
                return 1;
            case (byte) 0x57:
                System.out.print("pop");
                return 1;
            case (byte) 0x58:
                System.out.print("pop2");
                return 1;
            case (byte) 0xb5:
                System.out.print("putfield");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xb3:
                System.out.print("putstatic");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0xa9:
                System.out.print("ret");
                System.out.print(", index=" + in.readByte());
                return 2;
            case (byte) 0xb1:
                System.out.print("return");
                return 1;
            case (byte) 0x35:
                System.out.print("saload");
                return 1;
            case (byte) 0x56:
                System.out.print("sastore");
                return 1;
            case (byte) 0x11:
                System.out.print("sipush");
                System.out.print(", index=" + in.readShort());
                return 3;
            case (byte) 0x5f:
                System.out.print("swap");
                return 1;
            case (byte) 0xaa:
                System.out.print("tableswitch");
                throw new AssertionError();
            case (byte) 0xc4:
                System.out.print("wide");
                throw new AssertionError();
            default:
                System.out.print("UNKNOWN OP: " + Byte.toString(op));
        }

        return 1;
    }

    private void readAttributes(short attributeCount) throws IOException {
        for (short index = 0; index < attributeCount; index++) {
            readAttribute();
            System.out.println();
        }
    }

    private void readConstantPool(short constantPoolCount) throws IOException {
        for (short index = 1; index < constantPoolCount; index++) {
            System.out.print("	" + Integer.toString(index) + ", ");
            if (readConstantPoolEntry(index)) {
                index++;
                in.readByte();
            }
            System.out.println("#" + constantPoolCount);
        }
    }

    private boolean readConstantPoolEntry(int index) throws IOException {
        byte type = in.readByte();

        switch (type) {
            case 1:
                short length = in.readShort();
                byte[] buffer = new byte[length];
                in.readFully(buffer);
                String data = new String(buffer);

                strings.put(index, data);

                System.out.println("STRING: \"" + data + "\"");
                break;

            case 3:
                int intConstant = in.readInt();
                System.out.println("INT: \"" + Integer.toString(intConstant) + "\"");
                break;

            case 4:
                float floatConstant = in.readInt();
                System.out.println("FLOAT: \"" + Float.toString(floatConstant) + "\"");
                break;

            case 5:
                long longConstant = in.readInt();
                System.out.println("LONG: \"" + Long.toString(longConstant) + "\"");
                return true;

            case 6:
                double doubleConstant = in.readInt();
                System.out.println("DOUBLE: \"" + Double.toString(doubleConstant) + "\"");
                return true;

            case 7:
                short index1 = in.readShort();
                classRefs.put(index, (int) index1);
                System.out.println("CLASS REF: " + Short.toString(index1));
                break;

            case 8:
                index1 = in.readShort();
                stringRefs.put(index, (int) index1);
                System.out.println("STRING REF: " + Short.toString(index1));
                break;

            case 9:
                index1 = in.readShort();
                short index2 = in.readShort();
                System.out.println("FIELD REF: Class=" + Short.toString(index1) + ", NameType=" + Short.toString(index2));
                break;

            case 10:
                index1 = in.readShort();
                index2 = in.readShort();
                methodRefs.put(index, new int[]{index1, index2});
                System.out.println("METHOD REF: Class=" + Short.toString(index1) + ", NameType=" + Short.toString(index2));
                break;

            case 11:
                index1 = in.readShort();
                index2 = in.readShort();
                System.out.println("INTERFACE REF: Class=" + Short.toString(index1) + ", NameType=" + Short.toString(index2));
                break;

            case 12:
                index1 = in.readShort();
                index2 = in.readShort();
                System.out.println("NAMETYPE DESC: Name=" + Short.toString(index1) + ", Type=" + Short.toString(index2));
                break;

            case 15:
                in.readByte();
                in.readShort();

            case 16:
                in.readShort();
                break;

            case 18:
                in.readShort();
                in.readShort();
                break;

            case 0:
                in.readShort();
        }

        return false;
    }
}
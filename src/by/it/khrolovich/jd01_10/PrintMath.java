package by.it.khrolovich.jd01_10;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


class PrintMath {
    public static void main(String[] args) {
        Class<Math> mathClass = Math.class;
        //1.Публичные методы
        Method[] methods = mathClass.getDeclaredMethods();
        for (Method method : methods) {
            StringBuilder contract = new StringBuilder();
            //System.out.println(method);
            int modifiers = method.getModifiers();
            //только публичные
           /* if((modifiers&1)!=0){
                System.out.println(method);
            }*/
            //public static это 9 0b0000000000001001
            //только static 8 = 0b0000000000001000
            //&
            //получиться 8 0b0000000000001000
            /*if((modifiers&8)!=0){
                System.out.println(method);
            }*/
            if (Modifier.isPublic(modifiers)) {
                contract.append("public ");
            } else {
                continue;
            }
            if (Modifier.isPrivate(modifiers)) {
                contract.append("private ");
            }
            if (Modifier.isProtected(modifiers)) {
                contract.append("protected ");
            }
            if (Modifier.isStatic(modifiers)) {
                contract.append("static ");
            }

            Class<?> returnType = method.getReturnType();
            contract
                    .append(returnType.getName())
                    .append(' ')
                    .append(method.getName())
                    .append('(');
            Class<?>[] parameterTypes = method.getParameterTypes();
            String delimiter = "";
            for (Class<?> parameterType : parameterTypes) {
                contract.append(delimiter).append(parameterType.getName());
                delimiter = ",";
            }
            contract.append(')');
            System.out.println(contract);
        }
        //публичные поля
        Field[] fields = mathClass.getDeclaredFields();

        for (Field field : fields) {
            StringBuilder contract = new StringBuilder();
            int modifiers = field.getModifiers();

            if (Modifier.isPublic(modifiers)) {
                contract.append("public ");
            } else {
                continue;
            }
            if (Modifier.isPrivate(modifiers)) {
                contract.append("private ");
            }
            if (Modifier.isProtected(modifiers)) {
                contract.append("protected ");
            }
            if (Modifier.isStatic(modifiers)) {
                contract.append("static ");
            }

            contract.append(field.getType()).append(" ").append(field.getName());
            System.out.println(contract);
        }
    }
}

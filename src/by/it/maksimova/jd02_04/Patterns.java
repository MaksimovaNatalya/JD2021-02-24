package by.it.maksimova.jd02_04;

interface Patterns {

    String OPERATION = "(?<=[^-+*/=,{])[-+*/=]";
    String SCALAR = "-?[0-9]+(\\.[0-9]+)?";
    String VECTOR = "\\{" + SCALAR + "(," + SCALAR + ")*}";
    String MATRIX = "\\{" + VECTOR + "(," + VECTOR + ")*}";
    String EXPRESSION_IN_BRACKETS = "\\([^\\(]+?\\)";

}


    /*
    1111+1222
    2+2.0
    {-1,3,-5.0} + 4.0
    {1,3,5.0} + {2,3,4}
    {{1,2.0}, {3,4}} * {2,3}

    C=B+(A*2)

     */



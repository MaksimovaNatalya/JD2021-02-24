package by.it.savchenko.jd01_09;

import by.it.savchenko.jd01_09.Vector;

import java.util.Arrays;

public class Matrix extends by.it.savchenko.jd01_09.Var {
    private final double[][] value;

    @Override
    public by.it.savchenko.jd01_09.Var add(by.it.savchenko.jd01_09.Var other) {
        if (other instanceof by.it.savchenko.jd01_09.Scalar) {
            double[][] resMatrix = new double[value.length][0];
            for (int i = 0; i < resMatrix.length; i++) {
                resMatrix[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < resMatrix.length; i++) {
                for (int j = 0; j < resMatrix[0].length; j++) {
                    resMatrix[i][j] = resMatrix[i][j] + ((by.it.savchenko.jd01_09.Scalar) other).getValue();
                }
            }
            return new Matrix(resMatrix);
        }
        if (this.value.length == ((Matrix) other).value.length) {

            double[][] resMatrix = new double[value.length][0];
            for (int i = 0; i < value.length; i++) {
                resMatrix[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < resMatrix.length; i++) {
                for (int j = 0; j < resMatrix.length; j++) {
                    resMatrix[i][j] = resMatrix[i][j] + ((Matrix) other).value[i][j];
                }
            }
            return new Matrix(resMatrix);
        }
        return super.add(other);
    }

    @Override
    public by.it.savchenko.jd01_09.Var sub(by.it.savchenko.jd01_09.Var other) {
        if (other instanceof by.it.savchenko.jd01_09.Scalar) {
            double[][] resMatrix = new double[value.length][0];
            for (int i = 0; i < resMatrix.length; i++) {
                resMatrix[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < resMatrix.length; i++) {
                for (int j = 0; j < resMatrix[0].length; j++) {
                    resMatrix[i][j] = resMatrix[i][j] - ((by.it.savchenko.jd01_09.Scalar) other).getValue();
                }
            }
            return new Matrix(resMatrix);
        }
        if (this.value.length == ((Matrix) other).value.length) {

            double[][] resMatrix = new double[value.length][0];
            for (int i = 0; i < value.length; i++) {
                resMatrix[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < resMatrix.length; i++) {
                for (int j = 0; j < resMatrix.length; j++) {
                    resMatrix[i][j] = resMatrix[i][j] - ((Matrix) other).value[i][j];
                }
            }
            return new Matrix(resMatrix);
        }
        return super.add(other);
    }

    @Override
    public by.it.savchenko.jd01_09.Var mul(by.it.savchenko.jd01_09.Var other) {
        if (other instanceof by.it.savchenko.jd01_09.Scalar) {
            double s = ((by.it.savchenko.jd01_09.Scalar) other).getValue();
            double[][] resMatrix = new double[value.length][0];
            for (int i = 0; i < resMatrix.length; i++) {
                resMatrix[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < resMatrix.length; i++) {
                for (int j = 0; j < resMatrix.length; j++) {
                    resMatrix[i][j] *= s;
                }
            }
            return new Matrix(resMatrix);
        }
        if (other instanceof by.it.savchenko.jd01_09.Vector) {
            double[] s = ((by.it.savchenko.jd01_09.Vector) other).getValue();
            double[][] resMatrix = new double[value.length][0];
            for (int i = 0; i < resMatrix.length; i++) {
                resMatrix[i] = Arrays.copyOf(value[i], value[i].length);
            }
            double[] resArray = new double[resMatrix.length];
            for (int i = 0; i < resMatrix.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    resArray[i] += resMatrix[i][j] * s[j];
                }
            }
            return new Vector(resArray);
        }
        if (other instanceof Matrix) {
            double[][] secondArray = ((Matrix) other).getValue();
            double[][] firstArray = Arrays.copyOf(this.value, this.value.length);
            double[][] resultArray = new double[value.length][value[0].length];
            for (int i = 0; i < firstArray.length; i++) {
                for (int j = 0; j < secondArray[0].length; j++) {
                    for (int k = 0; k < secondArray.length; k++) {
                        resultArray[i][j] += firstArray[i][k] * secondArray[k][j];
                    }
                }
            }
            return new Matrix(resultArray);
        }
        return other.mul(this);
    }

    private double[][] getValue() {
        return value;
    }

    @Override
    public by.it.savchenko.jd01_09.Var div(by.it.savchenko.jd01_09.Var other) {
        if (other instanceof by.it.savchenko.jd01_09.Scalar) {
            double[][] resultMatrix = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < resultMatrix.length; i++) {
                for (int j = 0; j < resultMatrix[i].length; j++) {
                    resultMatrix[i][j] = resultMatrix[i][j] /= ((by.it.savchenko.jd01_09.Scalar) other).getValue();
                }
            }
            return new Matrix(resultMatrix);
        }
        return super.div(other);
    }

    Matrix(double[][] value) {
        this.value = new double[value.length][0];
        for (int i = 0; i < value.length; i++) {
            this.value[i] = Arrays.copyOf(value[i], value[i].length);
        }
    }

    Matrix(Matrix matrix) {
        this.value = matrix.value;
    }

    Matrix(String strMatrix) {
        String[] arrayRows = strMatrix.split("},"); // разбив матрицы на части
        int rowCount = arrayRows.length;
        int colCount = arrayRows[0].split(",").length;
        double[][] matrix = new double[rowCount][colCount];

        for (int i = 0; i < arrayRows.length; i++) {
            arrayRows[i] = arrayRows[i].replaceAll("[{}]", ""); // удаление лишних символов
        }

        for (int i = 0; i < matrix[0].length; i++) { // заполнение матрицы
            String[] arrayCols = arrayRows[i].split(",");
            for (int j = 0; j < arrayCols.length; j++) {
                matrix[i][j] = Double.parseDouble(arrayCols[j]);
            }
        }
        this.value = matrix;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append("{");
        for (int i = 0; i < value[0].length; i++) {
            resultString.append("{");
            for (int j = 0; j < value[1].length; j++) {
                resultString.append(value[i][j]);
                if (j != value.length - 1) {
                    resultString.append(", ");
                }
            }
            resultString.append("}");
            if (i != value[0].length - 1) {
                resultString.append(", ");
            }
        }
        resultString.append("}");
        return resultString.toString();
    }
}

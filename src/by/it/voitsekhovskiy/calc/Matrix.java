package by.it.voitsekhovskiy.calc;

import java.util.Arrays;

public class Matrix extends Var {
    private final double[][] value;

    @Override
    public Var add(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double[][] resultMatrix = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[i].length; j++) {
                    resultMatrix[i][j] = this.value[i][j] + ((Scalar) other).getValue();
                }
            }
            return new Matrix(resultMatrix);
        }
        if (other instanceof Matrix) {
            double[][] matrixLeft = this.value;
            double[][] matrixRight = ((Matrix) other).value;
            if (matrixLeft.length != matrixRight.length || matrixLeft[0].length != matrixRight[0].length) {
                throw new CalcException("not same size of matrix!");
            }
            double[][] resultMatrix = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[i].length; j++) {
                    resultMatrix[i][j] = this.value[i][j] + matrixRight[i][j];
                }
            }
            return new Matrix(resultMatrix);
        }
        return super.add(other);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double[][] resultMatrix = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < resultMatrix.length; i++) {
                for (int j = 0; j < resultMatrix[i].length; j++) {
                    resultMatrix[i][j] = this.value[i][j] - ((Scalar) other).getValue();
                }
            }
            return new Matrix(resultMatrix);
        }
        if (other instanceof Matrix) {
            double[][] matrixLeft = this.value;
            double[][] matrixRight = ((Matrix) other).value;
            if (matrixLeft.length != matrixRight.length || matrixLeft[0].length != matrixRight[0].length) {
                throw new CalcException("not same size of matrix!");
            }
            double[][] resultMatrix = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[i].length; j++) {
                    resultMatrix[i][j] = matrixLeft[i][j] - matrixRight[i][j];
                }
            }
            return new Matrix(resultMatrix);
        }
        return super.sub(other);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double[][] resultMatrix = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[i].length; j++) {
                    resultMatrix[i][j] = this.value[i][j] * ((Scalar) other).getValue();
                }
            }
            return new Matrix(resultMatrix);
        }
        if (other instanceof Vector) {
            double[] resultVector = new double[this.value.length];
            double[] entryVector = ((Vector) other).getValue();
            if (this.value.length != entryVector.length) {
                throw new CalcException("Not equal length of values!");
            }
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[i].length; j++) {
                    double multiplication = this.value[i][j] * entryVector[j];
                    resultVector[i] = resultVector[i] + multiplication;
                }
            }
            return new Vector(resultVector);
        }
        if (other instanceof Matrix) {
            double[][] matrixLeft = this.value;
            double[][] matrixRight = ((Matrix) other).value;
            if (matrixLeft[0].length != matrixRight.length) {
                throw new CalcException("Count columns of matrix A not equals count rows of matrix B ");
            }
            double[][] resultMatrix = new double[matrixLeft.length][matrixRight[0].length];
            for (int i = 0; i < matrixLeft.length; i++) {
                for (int j = 0; j < matrixRight[i].length; j++) {
                    double sum = 0;
                    for (int j1 = 0; j1 < matrixRight.length; j1++) {
                        sum = sum + (matrixLeft[i][j1] * matrixRight[j1][j]);
                        resultMatrix[i][j] = sum;
                    }
                }
            }
            return new Matrix(resultMatrix);
        }
        return super.mul(other);
    }

    @Override
    public Var div(Var other) throws CalcException {
        if (other instanceof Scalar) {
            if (((Scalar) other).getValue() == 0) {
                throw new CalcException("division by zero!");
            }
            double[][] resultMatrix = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < resultMatrix.length; i++) {
                for (int j = 0; j < resultMatrix[i].length; j++) {
                    resultMatrix[i][j] = this.value[i][j] / ((Scalar) other).getValue();
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

        for (int i = 0; i < matrix.length; i++) { // заполнение матрицы
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
        for (int i = 0; i < value.length; i++) {
            resultString.append("{");
            for (int j = 0; j < value[0].length; j++) {
                resultString.append(value[i][j]);
                if (j != value[0].length - 1) {
                    resultString.append(", ");
                }
            }
            resultString.append("}");
            if (i != value.length - 1) {
                resultString.append(", ");
            }
        }
        resultString.append("}");
        return resultString.toString();
    }
}

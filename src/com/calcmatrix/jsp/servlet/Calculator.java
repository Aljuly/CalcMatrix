package com.calcmatrix.jsp.servlet;

import org.ejml.simple.SimpleMatrix;

public class Calculator {

    InputData inputdata;
    OutputData outputdata;

    public void setInputdata(InputData inputdata) {
        this.inputdata = inputdata;
    }

    public OutputData getOutputdata() {
        
        SimpleMatrix A;
        SimpleMatrix B;
        
        class myReader {
            
            private double checkString(String string) {
                double res;
                try {
                    res = Double.parseDouble(string);
                } catch (Exception e) {
                    return 0;
                }
                return res;
            }
            
            double readNumber(int index) {
                return checkString(inputdata.getUserinputs().get(index).getValue()[0][0]);
            }
            
            double[][] readMatrix(int index) {
                int rows = inputdata.getUserinputs().get(index).getValue().length;
                int cols = inputdata.getUserinputs().get(index).getValue()[0].length;
                double[][] value = new double[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        value[i][j] = checkString(inputdata.getUserinputs().get(index).getValue()[i][j]);
                    }
                }
                return value;
            }
        }
        
        class myWriter {
            
            String[][] writeMatrix(SimpleMatrix S) {
                String[][] retVal = new String[S.numRows()][S.numCols()];
                for (int i = 0; i < S.numRows(); i++) {
                    for (int j = 0; j < S.numCols(); j++) {
                        retVal[i][j] = Double.toString(S.get(i, j));
                    }
                }
                return retVal;
            }
            
            String[][] writeNumber(double Val) {
                String[][] retVal = new String[1][1];
                retVal[0][0] = Double.toString(Val);
                return retVal;
            }
            
            String[][] writeMessage(String Val) {
                String[][] retVal = new String[1][1];
                retVal[0][0] = Val;
                return retVal;
            }
        }
        
        myReader reader = new myReader();
        myWriter writer = new myWriter();
        
        try {
            switch (inputdata.getCalcmode()) {
            
            case "1":
    
                A = new SimpleMatrix(reader.readMatrix(1));
                B = new SimpleMatrix(reader.readMatrix(3));
                A = A.scale(reader.readNumber(0));
                B = B.scale(reader.readNumber(2));
                outputdata = new OutputData("matrix", writer.writeMatrix(A.plus(B)));
                break;
    
            case "2":
                
                A = new SimpleMatrix(reader.readMatrix(1));
                B = new SimpleMatrix(reader.readMatrix(3));
                A = A.scale(reader.readNumber(0));
                B = B.scale(reader.readNumber(2));
                outputdata = new OutputData("matrix", writer.writeMatrix(A.mult(B)));
                break;
    
            case "3":
                
                A = new SimpleMatrix(reader.readMatrix(0));
                outputdata = new OutputData("matrix", writer.writeMatrix(A.invert()));
                break;
                
            case "4":
                
                A = new SimpleMatrix(reader.readMatrix(0));
                outputdata = new OutputData("matrix", writer.writeMatrix(A.transpose()));
                break;
                
            case "5":
                
                A = new SimpleMatrix(reader.readMatrix(0));
                outputdata = new OutputData("number", writer.writeNumber(A.determinant()));
                break;
            
            }
        } catch (Exception e) {
            outputdata = new OutputData("message", writer.writeMessage(e.getMessage()));
        }

        return outputdata;
    }
}

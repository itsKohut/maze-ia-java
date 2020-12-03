package br.com.java.estudo;

public class Network {
    private Neuron[] hiddenLayer;     //rede neural 8x8x4 -> topologia sugerida em aula
    private Neuron[] resultLayer;
    private double[] result;             //valores de saída da propagacao

    public Network(int numNeuroniosOculta, int numNeuroniosSaida) {
        if (numNeuroniosOculta <= 0 || numNeuroniosSaida <= 0) {
            numNeuroniosOculta = 8;
            numNeuroniosSaida = 4;
        }
        hiddenLayer = new Neuron[numNeuroniosOculta];
        resultLayer = new Neuron[numNeuroniosSaida];
    }

    public void setPesosNaRede(int numEntradas, double[] pesos) {
        int k = 0;
        double[] tmp;
        //Setando os pesos da camada oculta
        for (int i = 0; i < hiddenLayer.length; i++) {
            tmp = new double[numEntradas + 1];  //quantidade de pesos dos neurônios da camada oculta
            for (int j = 0; j < numEntradas + 1; j++) {
                tmp[j] = pesos[k];
                k++;
            }
            hiddenLayer[i] = new Neuron(tmp);
        }
        //Setando os pesos da camada de saida
        for (int i = 0; i < resultLayer.length; i++) {
            tmp = new double[hiddenLayer.length + 1];  //quantidade de pesos dos neurônios da camada oculta
            for (int j = 0; j < hiddenLayer.length + 1; j++) {
                tmp[j] = pesos[k];
                k++;
            }
            resultLayer[i] = new Neuron(tmp);
        }
    }

    public double[] propagation(double[] x) {

        int aaa = 0;
        if (x == null) return null;

        double[] saidaOculta = new double[hiddenLayer.length];
        result = new double[resultLayer.length];
        for (int i = 0; i < hiddenLayer.length; i++) {
            saidaOculta[i] = hiddenLayer[i].calculaY(x);
        }
        for (int i = 0; i < resultLayer.length; i++) {
            result[i] = resultLayer[i].calculaY(saidaOculta);
        }
        return result;
    }

    public String toString() {
        String msg = "Pesos da rede\n";
        msg = msg + "Camada Oculta\n";
        for (int i = 0; i < hiddenLayer.length; i++) {
            msg = msg + "Neuronio " + i + ": " + hiddenLayer[i] + "\n";
        }
        msg = msg + "Camada Saida\n";
        for (int i = 0; i < resultLayer.length; i++) {
            msg = msg + "Neuronio " + i + ": " + resultLayer[i] + "\n";
        }
        return msg;
    }
}

import argparse
import pandas as pd
import numpy as np
from datetime import datetime

def main():
    parser=argparse.ArgumentParser()
    parser.add_argument('-i', '--input', nargs='?',required=True, help='input file')
    parser.add_argument('-c', '--client', nargs='?',required=True, help='client IPv4 address')
    parser.add_argument('-d', '--delta', nargs='?',required=False, help='samplig delta interval (sec)',default=1)
    args=parser.parse_args()
    
    fileInput=args.input
    client=args.client
    deltams=float(args.delta)*1000

    print("Flows file: {}, Host: {}, Delta: {} ms".format(fileInput,client,deltams))

    data = pd.read_csv(fileInput)
    
    data=data.sort_values(by=['bidirectional_first_seen_ms'])

    flows_from_client=data[data['src_ip']==client]
   
    win=np.where(data['bidirectional_first_seen_ms']<1694013408122)
    data_win=data.loc[win][['dst_ip','bidirectional_first_seen_ms']]
   
    Ti=data.iloc[0]['bidirectional_first_seen_ms']
    Tf=data.iloc[-1]['bidirectional_first_seen_ms']
    print("First timestamp: {} - {}".format(Ti,datetime.utcfromtimestamp(Ti/1000)))
    print("Last timestamp: {} - {}".format(Tf,datetime.utcfromtimestamp(Tf/1000)))
    
    t=Ti
    obs=0

# A primeira coluna representa o número de fluxos que ocorreram durante o intervalo de tempo de 1 segundo.

# A segunda coluna representa a média do número de bytes enviados em todos os fluxos durante o intervalo de 1 segundo. Por exemplo, a primeira linha mostra que a média do número de bytes enviados foi 3133.83.

# A terceira coluna representa o desvio padrão do número de bytes enviados em todos os fluxos durante o intervalo de 1 segundo. Na primeira linha, o desvio padrão é 5100.88, o que indica a dispersão dos valores em relação à média.

# A quarta coluna representa a média do número de bytes recebidos em todos os fluxos durante o intervalo de 1 segundo. Na primeira linha, a média do número de bytes recebidos é 381746.0.

# A quinta coluna representa o desvio padrão do número de bytes recebidos em todos os fluxos durante o intervalo de 1 segundo. Na primeira linha, o desvio padrão é 920756.96.

# A sexta coluna representa a média da duração dos fluxos durante o intervalo de 1 segundo, em milissegundos. Na primeira linha, a média da duração é 27749.83.

# A sétima coluna representa o desvio padrão da duração dos fluxos durante o intervalo de 1 segundo, em milissegundos. Na primeira linha, o desvio padrão é 42295.69.

# As linhas com "0" ou "nan" indicam que não houve fluxos durante esse intervalo de tempo, portanto, não é possível calcular as estatísticas nesses casos.




    while t<Tf-deltams:
        cond1=data['bidirectional_first_seen_ms']>t
        cond2=data['bidirectional_first_seen_ms']<t+deltams
        win=np.where(cond1&cond2)
        
        nflows=data.iloc[win]['id'].count()
        
        avgupbytes=data.iloc[win]['dst2src_bytes'].mean()
        stdupbytes=data.iloc[win]['dst2src_bytes'].std()
        
        avgdownbytes=data.iloc[win]['src2dst_bytes'].mean()
        stddownbytes=data.iloc[win]['src2dst_bytes'].std()
        
        avgduration=(data.iloc[win]['bidirectional_last_seen_ms']-data.iloc[win]['bidirectional_first_seen_ms']).mean()
        stdduration=(data.iloc[win]['bidirectional_last_seen_ms']-data.iloc[win]['bidirectional_first_seen_ms']).std()
        
        print(nflows,avgupbytes,stdupbytes,avgdownbytes,stddownbytes,avgduration,stdduration)
        t+=deltams
        obs+=1

if __name__ == '__main__':
    main()

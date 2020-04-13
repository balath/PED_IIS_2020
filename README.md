# PED_IIS_2020 (Enunciado)

Realizar un programa orientado a objetos en Java que simule la expansión de un virus en una población siguiendo un modelo exponencial. 
 
El número de casos de infectados depende de: 
• El número de contactos que en promedio tenga cada infectado con personas no infectadas, lo llamaremos E. 
• La probabilidad de infectarse con un contacto, que llamaremos p. 
 
Si el número de infectados de una comunidad en un día d es Nd, el número de infectados el día siguiente Nd+1 será: 
                              Nd+1 = Nd + Nd *E*p = Nd * (1+E*p) 
 
Además, debemos pensar que vivimos en comunidades (ya sean países, pueblos o provincias) con una limitada interrelación unas con otras, lo que limita la expansión del virus de unas a otras. 
 
Supongamos que V es el número de viajeros diarios de una comunidad a otra, entonces el número de infectados en una comunidad por culpa de los viajeros Nv sería: 
                              Nv=E*p*V*Nd,origen/Porigen 
 
Donde Nd,origen sería número de infectados en la comunidad de origen del viajero y Porigen sería la población de la comunidad de origen. El cociente entre estos números representa la probabilidad de que el viajero esté infectado. 
 
Por lo tanto habrá que sumar Nd+1 y  Nv para saber el total de infectados en día d+1 en una comunidad. 
 
El programa debe permitir introducir los siguientes datos a usuario del programa: 
• Número de comunidades y la población de cada una de ellas. 
• Porcentaje de habitantes de cada comunidad que viaja diariamente a cada una de las otras comunidades, igual para todas las comunidades para simiplificar. 
• Coeficientes E, p y V. 
• Número de días a simular 
 
El programa debe devolver una tabla con los datos de infectados día a día en cada comunidad, el porcentaje de población infectada de cada comunidad, el número de infectados de la población total y el porcentaje de población total infectada. La presentación de los datos debe ser tal que se puedan analizar de forma pausada, apreciándose las representaciones gráficas. 
 
Una vez terminada una simulación, el programa debe permitir modificar cualquiera de los parámetros y volver a realizar la simulación. 
 
Se supone que el día uno aparece un primer infectado en una de las poblaciones

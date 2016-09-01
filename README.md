# FourierTransform
A study in the fourier transform

# FastFourierTransform
Digesting how the fast Fourier Transform works

###Defining the Fourier Transform
Power series are defined as the infinite series of terms. For example, sin(x) can be defined as  
  
Sin(x) = x - x^3/3! + x^5/5! - x^7/7! + ...  
  
Fourier series follow the same idea, except they are trigonometric series. Unlike power series where there are power terms, trigonometric series have trigonometric terms.
  
F(x) = A0 + A1 Cos(x) + A2 Cos(2x) + ...  
......... + B1 Sin(x) + B2 Sin(2x) + ...

###Explaining the Unit Square Wave
The Unit Square Wave must be defined in a piecewise function in which

f(x) =  1 for  0 <= x <= x1  
..... = -1 for x1 <= x <= x2

###Fourier Series and Unit Square Wave
As stated, Fourier Series are the addition of sine waves -- more specifically, harmonic sine waves. So, if within a defined interval, the sine wave goes through exactly one period, that wave would be called the first harmonic, if the sine waves goes through exactly two periods, that wave would be called the second harmonic, and so on. Also, Fourier Series only use odd harmonics, so the first, third, fifth, etc. harmonics.

So, to redefine the Fourier Series, it is the addition of odd harmonic sine waves.

![Adding the first, third, and fifth harmonics](http://i.imgur.com/Z8XXlrn.png)

As you can see, the more harmonics you add, the closer the function appears as a unit square wave.

![Adding a lot more harmonics](http://imgur.com/neORWdM.png)

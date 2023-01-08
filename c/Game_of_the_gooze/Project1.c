#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int roll();
int Human();
int Bot();
void Board(int,int);
int posH=0;
int posC=0;
bool sc(int);
#define BoardSize 24
#define goose +
#define bridge *
#define maze -
#define skull !

int board[BoardSize];

int i=0;


int main()
{
 int seed;
  char option;
    int h;
    int b;
    srand(seed);
for(i;i<BoardSize;i++){
   board[i]=i+1;
   }

  printf("Please enter a number: \n");
  scanf("%d",&seed);
  printf("press 'P' to play or 'Q' to quit\n");
  scanf(" %c",&option);



  switch(option){

      case 'p':
      case 'P':
                    while(1){
                         h =Human();
                         b= Bot();
                        if(h>b||b>h){

                            if(h>b){
                                printf("\nHUMAN PLAYER goes first\n");
                            }
                            else if(b>h){
                                printf("COMPUTER PLAYER goes first\n\n");
                            }
                            break;
                        }
                        else printf("Both the Human and Computer rolled the same value...");
                    }

        break;
      case 'q':
      case 'Q':
        break;
        default: break;

  }
        if(b>h){


            while(posH!=24 || posC!=24){
                        int temph;
                        int temp = Bot();
                         posC=posC+ temp;
                         bool x=1;


                 while(x==1){

                        if(posC>24){
                            int x = 24-(posC-temp);
                            posC= (posC-temp)+x-(temp-x);
                        }

                        if(posC==6){
                            posC=12;
                            printf("Go to space %d\n",posC);
                        }
                       else if(posC==7 || posC==11 || posC==15){

                            if(posC-temp==1 && posC==7){
                            int initPos=posC-temp;
                                posC=initPos;
                                printf("Go to space %d\n",posC);
                          }
                          else if((posC-temp==2 || posC-temp==9) && posC==11){
                            int initPos=posC-temp;
                                posC=initPos;
                                printf("Go to space %d\n",posC);
                          }
                          else if(posC-temp==10 && posC==15){
                                int initPos=posC-temp;
                                posC=initPos;
                                printf("Go to space %d\n",posC);
                            }
                            else if(posC>24){
                                int t = BoardSize-(posC-temp);
                                posC= BoardSize-(temp-t);
                            }
                            else if(posC>24){
                                int t = BoardSize-(posC-temp);
                                posC= BoardSize-(temp-t);
                            }
                            else{
                               posC=posC+temp;
                                if(posC>24){
                                    int x = 24-(posC-temp);
                                    posC= (posC-temp)+x-(temp-x);
                                }

                            printf("Go to space %d\n",posC);
                            }
                        }
                        else if(posC==13 || posC==20){
                            if(posC+temp>24){
                                int j = temp - (BoardSize-posC);
                                posC=BoardSize-j;
                            }else{
                            posC=posC-temp;
                            }
                        printf("Come back to space %d\n",posC);
                    }
                        else if(posC==23){
                            posC=1;
                            printf("Return to start\n");
                            break;
                        }
                        else{
                            printf("Go to space %d\n",posC);
                        }


                         x=sc(posC);


                        }

                         temph = Human();
                         posH=posH+ temph;
                         x=true;

                     while(x==1){

                        if(posH>24){
                            int x = 24-(posH-temph);
                            posH= (posH-temph)+x-(temph-x);
                        }

                        if(posH==6){
                            posH=12;
                            printf("Go to space %d\n",posH);
                        }
                       else if(posH==7 || posH==11 || posH==15){

                            if(posH-temph==1 && posH==7){
                            int initPos=posH-temph;
                                posH=initPos;
                                printf("Go to space %d\n",posH);
                          }
                          else if((posH-temph==2 || posH-temph==9) && posH==11){
                            int initPos=posH-temph;
                                posH=initPos;
                                printf("Go to space %d\n",posH);
                          }
                          else if(posH-temph==10 && posH==15){
                                int initPos=posH-temph;
                                posH=initPos;
                                printf("Go to space %d\n",posH);
                            }
                            else if(posH>24){
                                int t = BoardSize-(posH-temph);
                                posH= BoardSize-(temph-t);
                            }
                            else{
                              posH=posH+temph;
                                if(posH>24){
                                    int x = 24-(posH-temph);
                                    posH= (posH-temph)+x-(temph-x);
                                }

                            printf("Go to space %d\n",posH);
                            }
                        }
                        else if(posH==13 || posH==20){
                            if(posH+temph>24){
                                int j = temph - (BoardSize-posH);
                                posH=BoardSize-j;
                            }else{
                            posH=posH-temph;
                            }
                        printf("Come back to space %d\n",posH);
                    }
                        else if(posH==23){
                            posH=1;
                            printf("Return to start\n");
                            break;
                        }
                        else{
                        printf("Go to space %d\n",posH);
                        }
                        x=sc(posH);

                     }
                        if(posH==24){
                           Board(posH,posC);
                           break;
                        }
                        else if(posC==24){
                            Board(posH,posC);
                            break;
                        }
                        else{
                         printf("\n");
                        Board(posH,posC);
                        }

            }


        }


        else if(h>b){

            while(posH!=24 || posC!=24){
                    int temp;
                    int temph = Human();
                     posH=posH+ temph;
                     bool x=1;

                 while(x==1){

                    if(posH>24){
                        int x = 24-(posH-temph);
                        posH= (posH-temph)+x-(temph-x);
                    }

                    if(posH==6){
                        posH=12;
                        printf("Go to space %d\n",posH);
                    }
                   else if(posH==7 || posH==11 || posH==15){

                          if(posH-temph==1 && posH==7){
                            int initPos=posH-temph;
                                posH=initPos;
                                printf("Go to space %d\n",posH);
                          }
                          else if((posH-temph==2 || posH-temph==9) && posH==11){
                            int initPos=posH-temph;
                                posH=initPos;
                                printf("Go to space %d\n",posH);
                          }
                          else if(posH-temph==10 && posH==15){
                                int initPos=posH-temph;
                                posH=initPos;
                                printf("Go to space %d\n",posH);
                            }
                        else if(posH>24){
                           int t = BoardSize-(posH-temph);
                           posH= BoardSize-(temph-t);
                        }
                        else{

                              posH=posH+temph;
                                if(posH>24){
                                    int x = 24-(posH-temph);
                                    posH= (posH-temph)+x-(temph-x);
                                }

                            printf("Go to space %d\n",posH);

                        }
                    }

                    else if(posH==13 || posH==20){
                            if(posH+temph>24){
                                int j = temph - (BoardSize-posH);
                                posH=BoardSize-j;
                            }else{
                            posH=posH-temph;
                            }
                        printf("Come back to space %d\n",posH);
                    }
                    else if(posH==23){
                        posH=1;
                        printf("Return to start\n");
                    }
                    else{
                    printf("Go to space %d\n",posH);
                    }
                    x=sc(posH);
                 }


                     temp = Bot();
                     posC=posC+ temp;
                      x=1;

             while(x==1){

                    if(posC>24){
                        int x = 24-(posC-temp);
                        posC= (posC-temp)+x-(temp-x);
                    }

                    if(posC==6){
                        posC=12;
                        printf("Go to space %d\n",posC);
                    }
                   else if(posC==7 || posC==11 || posC==15){
                        if(posC-temp==1 && posC==7){
                            int initPos=posC-temp;
                                posC=initPos;
                                printf("Go to space %d\n",posC);
                          }
                          else if((posC-temp==2 || posC-temp==9) && posC==11){
                            int initPos=posC-temp;
                                posC=initPos;
                                printf("Go to space %d\n",posC);
                          }
                          else if(posC-temp==10 && posC==15){
                                int initPos=posC-temp;
                                posC=initPos;
                                printf("Go to space %d\n",posC);
                            }
                            else if(posC>24){
                                int t = BoardSize-(posC-temp);
                                posC= BoardSize-(temp-t);
                            }
                            else{
                            posC=posC+temp;
                            if(posC>24){
                                    int x = 24-(posC-temp);
                                    posC= (posC-temp)+x-(temp-x);
                                }

                            printf("Go to space %d\n",posC);
                            }
                    }
                    else if(posC==13 || posC==20){
                            if(posC+temp>24){
                                int j = temp - (BoardSize-posC);
                                posC=BoardSize-j;
                            }else{
                            posC=posC-temp;
                            }
                        printf("Come back to space %d\n",posC);
                    }
                    else if(posC==23){
                        posC=1;
                        printf("Return to start\n");
                    }
                    else{
                        printf("Go to space %d\n",posC);
                    }
                     x=sc(posC);

                    }

                                    if(posH==24){
                                           Board(posH,posC);
                                           break;
                                        }
                                    else if(posC==24){
                                            Board(posH,posC);
                                            break;
                                        }
                                    else{
                                         printf("\n");
                                        Board(posH,posC);
                                        }

            }




        }


}
  int roll(){

    int r = rand()%7;

    return r;

  }

  int Human(){
char c;
int sumOfDice=0;
  printf("\nHUMAN PlAYER, Press <enter> to roll the dice");
    fflush(stdin);


       scanf ("%c",&c);

        while(1){

                if(c==0x0A){
                    int roll1=roll();
                    int roll2=roll();
                    sumOfDice= roll1+roll2;
                    printf("\n%d and %d for a %d\n",roll1,roll2,sumOfDice);
                    return sumOfDice;
                }

        }
        return sumOfDice;
  }


   int Bot(){
char c=' ';
        int sumOfDice=0;
  printf("COMPUTER PlAYER, Press <enter> to roll the dice\n");


     while(1){

        scanf ("%c",&c);
            if(c==0x0A){
                int roll1=roll();
                int roll2=roll();
                sumOfDice= roll1+roll2;
                printf("%d and %d for a %d\n\n",roll1,roll2,sumOfDice);
                break;
            }

     }
        return sumOfDice;
  }


void Board(int P1, int P2){
int i;
if(P1==24){
    P1=23;
}
else if(P2==24){
    P2=23;
}
if(P1!=23){
    P1=P1-1;
}
if(P2!=23){
    P2=P2-1;
}

    for(i=0;i<BoardSize;i++){

        if(i==13){
            printf("\n");
        }
        if(i==5){
            printf("*[%d] ",board[i]);
        }

        else if(i==6 || i==10 || i==14){
            printf("[%d] ",board[i]);
        }

        else if(i==12 || i==19){
            printf("-[%d] ",board[i]);
        }

        else if(i==22){
            printf("![%d] ",board[i]);
        }



        else{
                if(P1==i && P2==i){
                    printf("[$%%] ");
                }
                else if(P1==23 && P1==i){
                  printf("<$>");
                }
                else if(P2==23 && P2==i){
                    printf("<%%>");
                }
                else if(i==BoardSize-1){
                    printf("<%d>",board[i]);
                }
                else if(P1==i){

                     printf("[$] ");
                }
                else if(P2==i){
                  printf("[%%] ");
                }
                else{
                printf("[%d] ",board[i]);
                }
        }

    }
    printf("\n");

}

bool sc(int pos){
    bool r;
    int spe[]={6,7,11,13,15,20,23};
        int i=0;
     while(i<7){

        if(pos==spe[i]){
            r=true;
            return r;
        }
        i++;
     }
    return false;

}

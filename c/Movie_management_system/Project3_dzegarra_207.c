#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct _movienode{

    char title[35];
    char genre[35];
    double duration;
    struct _movienode *next;
} movieNode;    

movieNode *head=NULL;   // MOVIE LIBRARY LIST
movieNode *wlhead=NULL; // WATCHLIST LIST

void watchlistMenu(int,char *title);
int libraryMenu(int selection, char *title);
void addMovieMenu(int,movieNode *newMovie);

movieNode* createmovieNode(char *title,char *genre, double duration);
int insertmovie(movieNode *head, movieNode *newNode, int position);
void printList(movieNode *head);
double computeDuration(movieNode *head);
movieNode *searchByTitle(movieNode *head, char *search);
movieNode *removemovie(movieNode *head, movieNode *remNode);
int deletemovie(movieNode *head, movieNode *delNode);
int getNodePosition(movieNode *head, char *search);
int savewatchlist(movieNode *head);
movieNode *loadwatchlist();
void appendmovie(movieNode *head, movieNode *newNode, int position);
void deleteMovieLists();
int LoadWatchlist(char *watchListName);

movieNode *createEmptyList();
void printmovieInfo(movieNode *movie);



void main(){               

int counter=0;
int counter2=0;

FILE *fp = fopen("movie_library.txt","r");

if(fp==NULL){
    printf("Error opening file!");
    exit(1);
}
    createEmptyList();

    movieNode *temp = (movieNode*)calloc(1,sizeof(movieNode));

  

    while(fgets(temp->title,25,fp)){

        counter2=0;
        if(counter>=1){
        fgets(temp->title,25,fp);       // read title 
        }
                                
        
            while(temp->title[counter2]!='\0'){

                   if(temp->title[counter2]=='\n'){
                       temp->title[counter2]='\0';
                   } 

                counter2++;
            }
        counter2=0;
        fgets(temp->genre,25,fp);    // read genre

            while(temp->genre[counter2]!='\0'){

                   if(temp->genre[counter2]=='\n'){
                       temp->genre[counter2]='\0';
                   } 

                counter2++;
            }

        fscanf(fp,"%lf",&temp->duration);        // read duration
        
        
   
        movieNode *mn=createmovieNode(temp->title,temp->genre,temp->duration);  
       
        if(head==NULL){
            head=mn;
        }
        else{
            appendmovie(head,mn,counter);
        }
        
       
        counter++;
       
    }
    fclose(fp);         //CHECK LATER

//=====WATCHLIST MENU===================   
while(1){
int selection;    
char titl[20];
char *searchTitle=(char*)malloc(sizeof(char)*20);
    printf("\n==================Watchlist Menu==================\n1. Print Watchlist\n2. Show Duration\n3. Search by title\n4. Move a movie up\n5. Move a movie down\n6. Remove a movie\n7. Save Watchlist\n8. Load Watchlist\n9. Go to movie Library\n10. Quit \n==================================================\n\n");
    printf("Make a selection: \n");
   
    scanf("%d",&selection);
    
    if(selection==3||selection==4||selection==5||selection==6){
        printf("Enter the title of the movie: ");
        
            fflush(stdin);
          scanf(" %[^\n]s", titl);
         strcpy(searchTitle,titl);
        watchlistMenu(selection,searchTitle);              
    }
    else
    watchlistMenu(selection,NULL);

}
//============================================
}


movieNode  *createmovieNode(char *title, char *genre, double duration){

       //struct _movienode* newNode;
        movieNode *newNode = (movieNode*)calloc(1,sizeof(movieNode));
        int counter=0;
        while(title[counter]!=0){
          newNode->title[counter]=title[counter];
          counter++;
        }
       
        counter=0;
        while(genre[counter]!=0){
          newNode->genre[counter]=genre[counter];
          counter++;
        }
        
        newNode->duration=duration;

    return newNode;
}

int insertmovie(movieNode *head, movieNode *newNode, int position){

int counter=1;
movieNode *temp = head;
while(counter<position){
    temp=temp->next;
    counter++;
}
if(temp->next!=NULL){

    newNode->next=temp->next;
    temp->next=newNode;
    return 1;
}
else if(temp->next==NULL){

    temp->next=newNode;
    return 1;
}
return 0;

}

void printmovieInfo(movieNode *movie){

printf("Movie title: %s\nMovie Genre: %s\nDuration of movie: %.2f\n\n",movie->title,movie->genre,movie->duration);

}

movieNode *createEmptyList(){

    movieNode *newList=NULL;
    return newList;

}

void appendmovie(movieNode *head, movieNode *newNode, int position){


    insertmovie(head,newNode,position);

    // if(head==NULL){
    //     head=newNode;
    // }
    // else{

    //     movieNode *temp=head;

    //     while(temp->next!=NULL){

    //         temp=temp->next;
            
    //     }
    //     temp->next=newNode;

    // }
}

void printList(movieNode *head){

if(head==NULL){
    printf("Your list is empty!\n");
    return;
}
movieNode *temp = head;
    while(temp!=NULL){
        
        printmovieInfo(temp);
      temp=temp->next;
    }

}

double computeDuration(movieNode *head){

int counter =0;
double sum=0;
movieNode *temp=head;
                                            //puts(head[0].title);
    if(head==NULL){
        return 0;
    }
    if(head->next==NULL){
        return head->duration;
    }
    else
    sum=sum+head->duration;
    
temp=head->next;
    while(temp!=NULL){
        
         sum=sum+(temp->duration);
        temp=temp->next;
        
    }
    return sum;
}

movieNode *searchByTitle(movieNode *head, char *search){
int counterDePrueba=0; //DELETE LATER
int charCounter=0;
movieNode *temp=head;
int len;
int tilen;
 int i;

if(head==NULL){
    
    return NULL;
}
len=strlen(search);

while(1){
    counterDePrueba=0;
    charCounter=0;
    tilen=strlen(temp->title);
    
      for(i=0;i<len;i++){
        if(temp->title[counterDePrueba]==search[counterDePrueba]){
            charCounter++;
           counterDePrueba++; 
        }
        else{
            break;
        }    
      }


      if(charCounter==len){
          return temp;
      }
 
    if(temp->next==NULL){
         
        return NULL;
    }
    temp=temp->next;
    
}

return NULL;

}

movieNode *removemovie(movieNode *head, movieNode *remNode){    // We remove from watchlist only

movieNode *temp;
 
        // if(head==remNode && head->next==NULL){
            
        //     head=NULL;

        //     printf("%d",1);
           
        //     return remNode;
        // }

        if(wlhead==remNode){          //FIX (REMOVES THE NODE AFTER, NOT remNode)
            // temp = head;
            // head=remNode->next;
            // free(temp);
          wlhead=wlhead->next;
            // printf("%d",2);
           printf("The movie was removed!\n");
            return remNode;
        }

        else if(remNode->next==NULL){
            temp = head;
            while(temp!=NULL){
                if(temp->next==remNode){
                    
                    break;
                }
                temp=temp->next;
            }
            
            temp->next=NULL;
         printf("The movie was removed!\n");
            printf("%d",3);
            // printf("%s was removed!",remNode->title);
            return remNode;
        }
        else{
            temp = head;
            while(temp!=NULL){
                if(temp->next==remNode){
                    break;
                }
                temp=temp->next;
            }
            temp->next=remNode->next;
            // remNode->next=NULL;
            printf("%d",4);
            printf("The movie was removed!\n");
            // printf("%s was removed!",remNode->title);
            return remNode;
        }

        return NULL;
    // }

}

void deleteMovieLists(){

    movieNode *temp;
    temp=wlhead;
    while(temp!=NULL){
        free(temp);
        temp=temp->next;
    }
    temp=head;
    while(temp!=NULL){
        free(temp);
        temp=temp->next;
    }

}

int LoadWatchlist(char *watchListName){


int counter=0;
int counter2=0;

FILE *fp = fopen(watchListName,"r");

if(fp==NULL){
    // printf("Watchlist does not exist!");
    return 0;
}
    wlhead=NULL;

    movieNode *temp = (movieNode*)calloc(1,sizeof(movieNode));

     while(fgets(temp->title,25,fp)){

        counter2=0;
        if(counter>=1){
        fgets(temp->title,25,fp);       // read title 
        }
                                
        
            while(temp->title[counter2]!='\0'){

                   if(temp->title[counter2]=='\n'){
                       temp->title[counter2]='\0';
                   } 

                counter2++;
            }
        counter2=0;
        fgets(temp->genre,25,fp);    // read genre

            while(temp->genre[counter2]!='\0'){

                   if(temp->genre[counter2]=='\n'){
                       temp->genre[counter2]='\0';
                   } 

                counter2++;
            }

        fscanf(fp,"%lf",&temp->duration);        // read duration
        
        
   
        movieNode *mn=createmovieNode(temp->title,temp->genre,temp->duration);  //creates a movie node to be added to the linked list
       
        if(wlhead==NULL){
            wlhead=mn;
        }
        else{
            appendmovie(wlhead,mn,counter);
        }
        
       
        counter++;
       
    }
   temp = wlhead;
   while(temp!=NULL){
       if(temp->next->next==NULL){
           break;
       }
       temp=temp->next;
   }
   temp->next=NULL;

    fclose(fp);         //CHECK LATER
 return 1;
}



void watchlistMenu(int selection, char *title){
    
   movieNode *temp;
   movieNode *mSearch;
   movieNode *oneBehind=NULL;
   movieNode *twoBehind=NULL;
   int charCounter=0;
   int counter=0;
   int len,i;
   char t[20];
   char titl[20];
   int selection2;
   char *searchTitle=(char*)malloc(sizeof(char)*20);

    switch(selection){

        case 1:  //print watchlist 
                if(wlhead==NULL){
                    printf("Your watchlist is empty!\n");
                }else{
                 printList(wlhead); 
                }
                break;

        case 2: printf("\nThe total duration of your watchlist is: %.2f \n\n",computeDuration(wlhead)); break; // Print total duration of watchlist

        case 3:     //search for a specific movie 
                temp=searchByTitle(wlhead,title);
                   if(temp!=NULL){
                         printf("\n>>>>>>>>>>>>>>>>>>>>>The movie is in your watchlist!<<<<<<<<<<<<<<<<<<<<<<\n\nName of movie: %s\nGenre: %s\nDuration: %.2f\n",temp->title,temp->genre,temp->duration);   

                   } 
                   else if(temp==NULL){
                       printf("The movie %s is not in your watchlist!\n");
                   }
                   break;

        case 4:   //move a movie up 

                ///////////////////////STRING COMPARATOR///////////////////////////////////////////////              
                len=strlen(title);
                    
                    for(i=0;i<len;i++){
                        if(wlhead->title[counter]==title[counter]){
                            charCounter++;
                        counter++; 
                        }
                        else{
                            break;
                        }    
                    }
                    if(charCounter==len){
                        printf("%s is already at the front of your list.",title);
                                    break;
                    }
                ////////////////////////////////////////////////////////////////////////////////////////
                 if(searchByTitle(wlhead,title)==NULL){
                     printf("The movie %s is not in your watchlist!",title);
                     break;
                 }
                    
                 else{
                    mSearch = searchByTitle(wlhead,title);              
                    oneBehind=wlhead;                                  
                                    
                     while(oneBehind!=mSearch){
                          if(oneBehind->next->next==mSearch){       
                                twoBehind=oneBehind;
                           }
                          if(oneBehind->next==mSearch){
                                  break;
                            }
                           oneBehind=oneBehind->next;
                     }
                    if(twoBehind==NULL){
                        
                        if(mSearch->next==NULL){
                            
                            mSearch->next=wlhead;
                            wlhead->next=NULL;
                           
                            printf("THe movie %s moved up!\n",title); 
                         
                      
                        }
                        else{
                            temp = createmovieNode(wlhead->title,wlhead->genre,wlhead->duration);
                            temp->next=mSearch->next;
                            mSearch->next=temp;
                            wlhead=mSearch;
                          printf("THE movie %s moved up!\n",title);  
                        }

                        
                        break;
                      }
                      
                    else  if(mSearch->next==NULL){
                           
                           mSearch->next=oneBehind;
                           twoBehind->next=mSearch;
                           oneBehind->next=NULL; 
                           printf("ThE movie %s moved up!\n",title);
                           break;
                    }
                      
                      
                     else{
                          
                        oneBehind->next=mSearch->next;
                        mSearch->next=oneBehind;
                        twoBehind->next=mSearch;
                        

                        printf("The Movie %s moved up!\n",title);
                        break;
                     }

                 }
                    break;
           

        case 5:   //move a movie down 

                ///////////////////////HEAD TITLE COMPARATOR///////////////////////////////////////////////              
                len=strlen(title);
                    
                    for(i=0;i<len;i++){
                        if(wlhead->title[counter]==title[counter]){
                            charCounter++;
                        counter++; 
                        }
                        else{
                            break;
                        }    
                    }

                    if(charCounter==len && wlhead->next->next==NULL){ //DONE
                            temp = createmovieNode(wlhead->title,wlhead->genre,wlhead->duration);
                                    
                                    wlhead->next->next=temp;
                                    temp->next=NULL;
                                    wlhead=wlhead->next;
                                    printf("The movie %s moved down!",title);
                                    break;
                    }
                    else if(charCounter==len){  //DONE
                                                                
                                                            
                            temp=wlhead->next;
                            wlhead->next=wlhead->next->next;
                            temp->next=wlhead;
                            wlhead=temp;

                            printf("THe movie %s moved down!",title);
                            break;
                        }
                ////////////////////////////////////////////////////////////////////////////////////////


                if(searchByTitle(wlhead,title)==NULL){  //DONE
                     printf("The movie %s is not in your watchlist!",title);
                     break;
                 }

                else if(wlhead->next==NULL){    //DONE
                    printf("The movie %s is the only movie in your watchlist!\n",title);
                    break;
                }
                
                else {
                    temp = searchByTitle(wlhead,title);

                    if(temp->next==NULL){   //DONE
                        printf("The movie %s is already at the bottom of your watchlist!");
                        break;
                    }

                    else if(temp->next->next==NULL){
                         
                        mSearch=wlhead;
                        while(mSearch!=temp){
                            if(mSearch->next==temp){
                                break;
                            }
                            mSearch=mSearch->next;
                        }
                        mSearch->next=temp->next;
                        temp->next->next=temp;
                        temp->next=NULL;
                        printf("The movie %s moved down!",title);
                        break;

                    }
                    else{        
                        mSearch=wlhead;
                        while(mSearch!=temp){
                            if(mSearch->next==temp){
                                break;
                            }
                            mSearch=mSearch->next;  
                        }
                        mSearch->next=temp->next;
                        temp->next=temp->next->next;
                        mSearch->next->next=temp;
                       
                        printf("The movie %s moved down!",title);
                        break;

                    }


                }
        
             break;     

        case 6:    //remove a movie
               

                if(searchByTitle(wlhead,title)==NULL){
                    printf("The movie %s is not in your watchlist.",title);
                    temp=searchByTitle(wlhead,searchTitle);
                    removemovie(wlhead,temp);
                   
                 }else{
                    
                    temp = searchByTitle(wlhead,title);
                    if(wlhead==temp && wlhead->next==NULL){
                        printf("The movie was removed!\n");
                        wlhead=NULL;
                    }
                    else
                    removemovie(wlhead,temp);

                 }
        
        
                      break;   

        case 7:    // save watchlist
        if(wlhead==NULL){
            printf("Your watchlist is empty!");
            break;
        }
        printf("Please enter a name for the watchlist to be saved: ");
        scanf(" %[^\n]s", titl);
            
            FILE *fp;
            fp=fopen(titl,"r");
            if(fp!=NULL){
                strcpy(t,titl);
            }

            while(strcmp(t,titl)==0){
                printf("A file named %s already exist!\nPlease choose a different name: ",titl);
                scanf(" %[^\n]s", titl);
               
                    
            }
            if(fp!=NULL){
            fclose(fp);
            }

            fp = fopen(titl,"w+");
            temp = wlhead;
            while(temp!=NULL){                
                fprintf(fp,"%s\n%s\n%.2lf\n",temp->title,temp->genre,temp->duration);
                temp=temp->next;
            }
            fclose(fp);
            printf("Watchlist %s successfully saved!\n",titl);

                    break;  

        case 8:      // load watchlist

                printf("Enter the name of the watchlist you wish to load: ");
                scanf(" %[^\n]s", titl);
                strcpy(searchTitle,titl);
                 
                if(LoadWatchlist(searchTitle)!=0){
                    printf("Watchlist successfully loaded!");
                    
                }
                else{
                    printf("%s does not exist!");
                    
                }
        
        
                    break;   

        case 9:     //go to movie library
                
               while(1){ 
                printf("\n\n==================Movie Library Menu==================\n1. View all movies\n2. Search by title\n3. Add a movie to watchlist\n4. Back to watchlist\n==================================================\n");
                printf("Make a selection: ");
                scanf("%d",&selection2);

                if(selection2==2 || selection2==3){
                    printf("Enter the title of the movie: ");
                    
                    scanf(" %[^\n]s", titl);
                    strcpy(searchTitle,titl);

                    libraryMenu(selection2,searchTitle);


                }
                else{
                    if(libraryMenu(selection2,NULL)==1){
                        
                        break;
                    }
                }
                
               }
                break;

        case 10:   
        deleteMovieLists();
        
                 exit(0);
        default: break;

    }

}  

int libraryMenu(int selection, char *title){             

 movieNode *temp;
 movieNode *movieToAdd;
 int selection2;
int ret;

    switch(selection){

        case 1: printList(head); ret=0; break; //prints movie library
        case 2: //search for a specific movie
               temp=searchByTitle(head,title);
                   if(temp!=NULL){
                         printf("\n>>>>>>>>>>>>>>>>>>>>>The movie is in your library!<<<<<<<<<<<<<<<<<<<<<<\nName of movie: %s\nGenre: %s\nDuration: %.2lf\n\n",temp->title,temp->genre,temp->duration);   

                   } 
                   else{
                       printf("\nThe movie %s is not in your library.\n");
                   }ret=0;
                   break;

        case 3:        //add a movie to watchlist
                
                if(searchByTitle(head,title)==NULL){
                    printf("The movie ""%s"" is not in your library.\n");break;
                }
                else if(searchByTitle(wlhead,title)!=NULL){
                    printf("The movie %s is already in your watchlist!",title);break;
                }
                else{
                    movieToAdd=searchByTitle(head,title);
                }
                printf("\nPlease select in the menu below where you would like to add the movie.\n");
                printf("==================Add Movie Menu==================\n1. Add movie at the end\n2. Add movie at the front\n3. Insert movie at a specific position\n==================================================\n");
                printf("Make a selection: ");
                scanf("%d",&selection2);
                addMovieMenu(selection2,movieToAdd);
               
                
                ret=0;
                break;
         
         
        case 4:  ret=1;    break;   //Back to watchlist
      default: break;


    }
    return ret;

}

void addMovieMenu(int selection,movieNode *newMovie){             

int counter=0;
int position;
movieNode *temp;
movieNode *movieToAdd;
movieNode *case3Node;

    switch(selection){

        case 1: //add movie to the end
                if(wlhead==NULL){
                    movieToAdd=createmovieNode(newMovie->title,newMovie->genre,newMovie->duration);
                    wlhead=movieToAdd;
                }

                else{
                    temp = wlhead;
                    movieToAdd=createmovieNode(newMovie->title,newMovie->genre,newMovie->duration);
                    while(temp!=NULL){
                        if(temp->next==NULL){
                            break;
                        }
                        temp=temp->next;
                    }
                    temp->next=movieToAdd;
                 

                }   
                printf("Movie has been added!");
                break;
        case 2:   //add movie to the beginning 
                if(wlhead==NULL){
                    movieToAdd=createmovieNode(newMovie->title,newMovie->genre,newMovie->duration);
                    wlhead=movieToAdd;                 
                }
                
                else{
                    movieToAdd=createmovieNode(newMovie->title,newMovie->genre,newMovie->duration);
                    temp = createmovieNode(wlhead->title,wlhead->genre,wlhead->duration);
                    movieToAdd->next=temp;
                  
                    temp->next=wlhead->next;

                    wlhead=movieToAdd;                
                }    
                printf("Movie has been added!");
                break;
        case 3:        
                printf("Please enter in which position you want the movie to be added: ");
                scanf("%d",&position);
                while(position<0){
                    printf("Please, enter a non-negative position.\n");
                    scanf("%d",&position);
                }
                    if(wlhead==NULL){
                        if(position>0){
                            printf("The list is empty. The movie %s will be the first movie in your list!",newMovie);
                            wlhead=newMovie;
                        }
                    }
                    else{
                            temp=wlhead;
                                                while(temp!=NULL){
                                                    counter++;
                                                    temp=temp->next;
                                                    
                                                }
                                                if(position>counter){
                                                    printf("The movie cannot be added at that position. The movie will be added at the end of the list.\n");
                                                    addMovieMenu(1,newMovie);
                                                    break;
                                                }
                                                else{
                                                   
                                                    counter=0;
                                                    while(counter<=position){
                                                        
                                                        if(counter==0){
                                                          temp=wlhead;
                                                          counter++;
                                                        }
                                                        else{
                                                        temp=temp->next;
                                                        counter++;
                                                        }
                                                    }
                                                    if(position==0){
                                                        case3Node = createmovieNode(temp->title,temp->genre,temp->duration);
                                                        newMovie->next=case3Node;
                                                        case3Node->next=temp->next;
                                                        wlhead=newMovie;
                                                        printf("The movie was added to your watchlist!");
                                                        break;
                                                    }
                                                    newMovie->next=temp->next;
                                                    temp->next=newMovie;
                                                
                                                }

                       
                        printf("The movie was added to your watchlist!");
                    }
                        break;
                    
                    

       default: break;
        
    }


}











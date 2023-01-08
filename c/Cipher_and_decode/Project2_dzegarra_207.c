#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void menu();
void choice1();
void choice2();
void choice3();

unsigned short maxWords = 5000;
unsigned short maxChars = 15;

char **content = NULL;

int wordCounter=0;

short choise1ok = 0;

int main()
{
    int choice;

    while(1){
            menu();
            printf("Please make a selection: ");

            scanf(" %d",&choice);
            getchar();
            fflush(stdin);
            switch(choice){

        case 1: choice1(); break;

        case 2: choice2();break;

        case 3: choice3();break;

        case 4: printf("End program\n");exit(1);break;

            default:break;

            }
    }

  // printf("wordCounter: %d\n",wordCounter);
}

void menu(){
    printf("\n################### MENU ###################\n");
    printf("1) Read in the name of a text file to use as a cipher key\n2) Create a cipher using the input text file(and save the result to a file)\n3) Decode an existing cipher(prompt user for a file to read containing the cipher text\n4) Exit the program\n");
}

void choice1(){
    FILE *fp;
    int i;

    char *nameFile = (char *)calloc(40, sizeof(char)); //stores name of the file to access

    char *word = (char *)calloc(15, sizeof(char));

    content = (char **)calloc(maxWords, sizeof(char *));
    if(content == NULL){
        printf("Error, memory couldn't be created\n");
        exit(0);
    }
    for(i=0; i<maxWords; i++){
        content[i] = (char *)calloc(maxChars, sizeof(char));
        if(content[i] == NULL){
            printf("Error, memory couldn't be created\n");
            exit(0);
        }
    }

    printf("Please enter the name of the text file: ");
    fgets(nameFile, 40*sizeof(char), stdin);

    for(i=0; i<40*sizeof(char); i++){
        if(nameFile[i] == '\n'){
            nameFile[i] = '\0';
            break;
        }
    }

    fp = fopen(nameFile,"r");

    if(fp==NULL){
        printf("\nError: Unable to open file.\n");
    }

    while(fscanf(fp,"%s",word)==1){  //Reads formatted input from a file
        for(i=0; i<maxChars; i++){
            if(word[i] != 0){
                content[wordCounter][i] = tolower(word[i]);
            }else{
                break;
            }
        }
        wordCounter++;
        if(wordCounter >= 5000){
            break;
        }
    }
    choise1ok = 1;
    fclose(fp);
    free(nameFile);
    free(word);
    printf("Number of words in the encoding text: %d\n",wordCounter);
}

void choice2(){
    int i,l,j,k;

    FILE *fileOut;
    char *nameFile = (char *)calloc(40, sizeof(char));
    if(choise1ok == 0){ //
        char *token;
        printf("Since you didn't previously select choice 1, enter the encoding text.\nInsert the text: ");
        char *textInput = (char *)calloc(maxChars*maxWords, sizeof(char));
        content = (char **)calloc(maxWords, sizeof(char *));
        if(content == NULL){
            printf("Error, memory couldn't be created\n");
            exit(0);
        }
        for(i=0; i<maxWords; i++){
            content[i] = (char *)calloc(maxChars, sizeof(char));
            if(content[i] == NULL){
                printf("Error, memory couldn't be created\n");
                exit(0);
            }
        }
        fgets(textInput, maxWords*maxChars*sizeof(char), stdin);
        token = strtok(textInput, " ");
        while(token != NULL){
            for(i=0; i<maxChars; i++){
                if((token[i] != 0) || (token[i] != 10) || (token[i] != 13)){
                    content[wordCounter][i] = (char)tolower(token[i]);
                }else{
                    break;
                }
            }
            wordCounter++;
            token = strtok(NULL, " ");
        }
        free(textInput);
        free(token);
        printf("Number of words in the encoding text: %d\n",wordCounter);
    }

    char *message = (char*)calloc(1500, sizeof(char));
    printf("\nInsert the message: ");
    fgets(message, 1500*sizeof(char), stdin);

    char *encoded = (char*)calloc(1500*7, sizeof(char));
    unsigned int nEncoded = 0;
    unsigned int charEncodedCounter = 0;
    char *token;
    char c;
    token = strtok(message, " ");
    char **temporalArray = NULL;
    while(token != NULL){
        for(i=0; i<strlen(token); i++){
            c = tolower(token[i]);

            if((c == 10) || (c == 0)){
                break;
            }else{
                temporalArray = (char**)calloc(1500, sizeof(char));
                charEncodedCounter = 0;
                if(temporalArray == NULL){
                    printf("Error, memory couldn't be created\n");
                    exit(0);
                }

                for(l=0; l<1500; l++){
                    temporalArray[l] = (char *)calloc(7, sizeof(char));
                    if(temporalArray[l] == NULL){
                        printf("Error, memory couldn't be created\n");
                        exit(0);
                    }
                }

                for(j=0; j<wordCounter; j++){
                    for(k=0; k<maxChars; k++){
                        if((content[j][k] == 0) || (content[j][k] == 10)){
                            break;
                        }else{
                            if(c == content[j][k]){
                                char *temp1 = (char *)calloc(7, sizeof(char)); // j,k -> word,character
                                sprintf(temp1,"%d,%d", j,k);
                                for(l=0; l<strlen(temp1); l++){
                                    temporalArray[charEncodedCounter][l] = temp1[l];
                                }
                                for(l=strlen(temp1); l<7; l++){
                                    temporalArray[charEncodedCounter][l] = 0;
                                }
                                free(temp1);

                                charEncodedCounter++;
                            }
                        }
                    }
                }
                if(charEncodedCounter == 0){
                    encoded[nEncoded] = '#';
                    nEncoded++;
                    encoded[nEncoded] = ',';
                    nEncoded++;
                }else{
                    int ti = (rand()%charEncodedCounter);
                    for(l=0; l<strlen(temporalArray[ti]); l++){
                        encoded[nEncoded] = temporalArray[ti][l];
                        nEncoded++;
                    }
                    encoded[nEncoded] = ',';
                    nEncoded++;

                }

                for(l=0; l<1500; l++){
                    if(temporalArray != NULL){
                        temporalArray[l] = NULL;
                    }
                }
                if(temporalArray != NULL){
                    temporalArray = NULL;
                }
            }
        }
        nEncoded--;
        encoded[nEncoded] = ' ';
        nEncoded++;
        token = strtok(NULL, " ");
    }
    nEncoded--;
    encoded[nEncoded] = ' ';
    printf("Encoded message: %s\n",encoded);
    printf("Please enter the name of the output file: ");
    fgets(nameFile, 40*sizeof(char), stdin);

    fileOut = fopen(nameFile, "w");
    fwrite(encoded, nEncoded, sizeof(char), fileOut);
    fclose(fileOut);
    free(nameFile);
    printf("File created\n");
    if(choise1ok == 0){
        free(content);
    }
}

void choice3(){

int i;
    FILE *fileOpen;
    char *nameFile = (char *)calloc(40, sizeof(char));
    char *charsEnconded = (char *)calloc(5000, sizeof(char));
    printf("Please enter the name of the file to decode: ");
    fgets(nameFile, 40*sizeof(char), stdin);
    fileOpen = fopen(nameFile,"r");
    if(fileOpen==NULL){
        printf("\nError: Unable to open file.\n");
    }
    if(choise1ok == 0){
        wordCounter = 0;
        char *token;
        printf("Since you didn't previously select choise 1, enter the encoding text.\nInsert the text: ");
        char *textInput = (char *)calloc(maxChars*maxWords, sizeof(char));
        content = (char **)calloc(maxWords, sizeof(char *));
        if(content == NULL){
            printf("Error, memory couldn't be created\n");
            exit(0);
        }
        for(i=0; i<maxWords; i++){
            content[i] = (char *)calloc(maxChars, sizeof(char));
            if(content[i] == NULL){
                printf("Error, memory couldn't be created\n");
                exit(0);
            }
        }
        fgets(textInput, maxWords*maxChars*sizeof(char), stdin);
        token = strtok(textInput, " ");
        while(token != NULL){
            for(i=0; i<maxChars; i++){
                if((token[i] != 0) || (token[i] != 10) || (token[i] != 13)){
                    content[wordCounter][i] = (char)tolower(token[i]);
                }else{
                    break;
                }
            }
            wordCounter++;
            token = strtok(NULL, " ");
        }

        free(textInput);
        free(token);
        printf("Number of words in the encoding text: %d\n",wordCounter);
    }


    char *messageEncoded = (char *)calloc(1500*7,sizeof(char));
    fread(messageEncoded, 1500*7, sizeof(char), fileOpen);
    printf("Message read: %s\n", messageEncoded);


    char *messageDecoded = (char *)calloc(1500, sizeof(char));
    int nDecoded = 0;

    char *wordToken;
    char *numberWord = NULL;
    int nWord = 0;
    char *numberChar = NULL;
    int nChar = 0;
    int intWord = 0;
    int intChar = 0;
    short action = 0;
    wordToken = strtok(messageEncoded, " ");
    while(wordToken != NULL){

        numberWord = (char *)calloc(4, sizeof(char));
        numberChar = (char *)calloc(3, sizeof(char));
        for(i=0; i<strlen(wordToken); i++){

            if(wordToken[i] != 0){
                if(wordToken[i] == '#'){
                    messageDecoded[nDecoded] = '#';
                    nDecoded++;
                    if(wordToken[i+1]==','){
                        i++;
                    }
                }else if (wordToken[i] == ' '){
                    messageDecoded[nDecoded] = ' ';
                    nDecoded++;
                }else{
                    if(wordToken[i] == ','){
                        if(action == 1){

                            nChar = 0;
                            intWord = atoi(numberWord);
                            intChar = atoi(numberChar);
                            messageDecoded[nDecoded] = content[intWord][intChar];
                            nDecoded++;

                            action = 0;
                            free(numberWord);
                            free(numberChar);
                            numberWord = (char *)calloc(5, sizeof(char));
                            numberChar = (char *)calloc(3, sizeof(char));
                        }else if(action == 0){

                            action = 1;
                            nWord = 0;
                        }

                    }else{
                        if(action == 0){
                            numberWord[nWord] = wordToken[i];
                            nWord++;
                        }else if(action == 1){
                            numberChar[nChar] = wordToken[i];
                            nChar++;
                        }
                    }
                    if(i == (strlen(wordToken)-1)){

                        nChar = 0;
                        intWord = atoi(numberWord);
                        intChar = atoi(numberChar);
                        messageDecoded[nDecoded] = content[intWord][intChar];
                        nDecoded++;

                        action = 0;
                        free(numberWord);
                        free(numberChar);
                        numberWord = (char *)calloc(5, sizeof(char));
                        numberChar = (char *)calloc(3, sizeof(char));
                        messageDecoded[nDecoded] = ' ';
                        nDecoded++;
                    }

                }
            }else{
                break;
            }
        }
        wordToken = strtok(NULL, " ");
    }
    printf("Message decoded: %s\n",messageDecoded);

}

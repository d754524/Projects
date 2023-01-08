/* This is a basic linkedlist implementation 
 * program in C
 */
#include<stdlib.h>
#include<stdio.h>

typedef struct Node
{
	int data;
	struct Node *next;
}node;

node *startPtr = NULL;

void insertNode(int newData)
{
	if(startPtr == NULL)
	{
		node *newNode =  (node*)malloc(sizeof(node));
		if(newNode == NULL)
		{
			printf("ERROR\n");
			exit(1);
		}

		newNode->data = newData;
		newNode->next = NULL;
	        startPtr = newNode; 
	}
	else
	{
		node *newNode = (node*)malloc(sizeof(node));
		if(newNode == NULL)
		{
			printf("ERROR\n");
			exit(1);
		}
		newNode->data = newData; 
		newNode->next = startPtr; 
		startPtr = newNode;
	}
}

int deleteNode(int oldData)
{
	node *currentNode = NULL;
	node *previousNode = NULL;
	int result = 0;

	if(startPtr == NULL)
		result = 0; 
	else
	{
		currentNode = startPtr;

		while (currentNode != NULL)
		{
			if(currentNode-> data == oldData)
			{
				if(previousNode == NULL)
				{
					startPtr = startPtr->next;
					result = 1;
					break;
				}
				else if(currentNode->next == NULL)
				{
					previousNode->next = NULL;
					result = 1; 
					break;
				}
				else
				{
					previousNode->next = currentNode->next;
					currentNode->next = NULL;
					result = 1;
					break;
				}
			}
			else
			{
				previousNode = currentNode;
				currentNode = currentNode->next;
			}
		}
	}
			return result; 
}

void display()
{
	node *currentNode = startPtr;

	while(currentNode !=NULL)
	{
		printf("%d\t", currentNode->data);
		currentNode = currentNode->next;
	}
	printf("\n");
}

int main (void)
{
	insertNode(10);
       insertNode(25);//	add(25);
	insertNode(6); //add(6);
	insertNode(12); //add(12);
	insertNode(33);
	insertNode(89);
	insertNode(56);
	display();
	deleteNode(12);
	display();
	deleteNode(10);
	display();
	deleteNode(56);
	display();
	
	return 0;
	}

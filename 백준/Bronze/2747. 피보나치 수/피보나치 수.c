#include <stdio.h>

int f(int a)
{
	if (a == 0)
		return 0;
	else if (a == 1 || a==2)
		return 1;

	else
	{
		int p = 0, q = 1, sum = 0, i;

		for (i = 1; i < a;i++)
		{
			sum = p + q;
			p = q;
			q = sum;
		}
		return sum;
	}
}
int main()
{
	int i;

	scanf("%d", &i);
	if (i == 0)
		printf("%d", 0);
	else
		printf("%d", f(i));

	return 0;
}
import { NextResponse } from 'next/server';

export async function GET() {
  try {
    const response = await fetch('http://localhost:8080/api/v1/benchmarks', {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const data = await response.json();
    return NextResponse.json(data);
  } catch (error) {
    return NextResponse.json(
      { error: 'Error fetching benchmarks' },
      { status: 500 }
    );
  }
}

export async function POST(request) {
    try {
        const body = await request.json();
        
        const response = await fetch('http://localhost:8080/api/v1/benchmarks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(body),
        });

        if (response.status === 201) {
            return NextResponse.json({ 
                message: 'Benchmark criado com sucesso' 
            });
        }

        if (!response.ok) {
            const errorData = await response.json();
            return NextResponse.json(
                errorData,
                { status: response.status }
            );
        }

        throw new Error(`Erro na API: ${response.status}`);
    } catch (error) {
        console.error('Erro ao criar benchmark:', error);
        return NextResponse.json(
            { error: 'Erro ao criar benchmark: ' + error.message },
            { status: 500 }
        );
    }
}
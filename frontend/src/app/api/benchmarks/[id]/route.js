import { NextResponse } from 'next/server';

export async function DELETE(request, { params }) {
    try {
        const resolvedParams = await params;
        const id = resolvedParams.id;
        
        const response = await fetch(`http://localhost:8080/api/v1/benchmarks/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });
  
        if (response.status === 204 || response.status === 200) {
            return NextResponse.json({ 
                message: 'Benchmark excluído com sucesso' 
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
        console.error('Erro ao deletar benchmark:', error);
        return NextResponse.json(
            { error: 'Erro ao deletar benchmark: ' + error.message },
            { status: 500 }
        );
    }
  }
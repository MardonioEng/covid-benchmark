import { NextResponse } from 'next/server';
import { API_URL } from '../../../../config/api';

export async function GET(request, { params }) {
    try {
        const resolvedParams = await params;
        const id = resolvedParams.id;
        
        const response = await fetch(`${API_URL}/benchmarks/${id}`, {
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            const data = await response.json();
            return NextResponse.json(data);
        }

        const errorData = await response.json();
        return NextResponse.json(
            errorData,
            { status: response.status }
        );
    } catch (error) {
        console.error('Erro ao buscar benchmark:', error);
        return NextResponse.json(
            { error: 'Erro ao buscar benchmark: ' + error.message },
            { status: 500 }
        );
    }
}

export async function DELETE(request, { params }) {
    try {
        const resolvedParams = await params;
        const id = resolvedParams.id;
        
        const response = await fetch(`${API_URL}/benchmarks/${id}`, {
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

  export async function PUT(request, { params }) {
    try {
        const resolvedParams = await params;
        const id = resolvedParams.id;
        const body = await request.json();
        
        const response = await fetch(`${API_URL}/benchmarks/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name: body.name }),
        });

        if (response.ok) {
            const data = await response.json();
            return NextResponse.json(data);
        }

        const errorData = await response.json();
        return NextResponse.json(
            errorData,
            { status: response.status }
        );
    } catch (error) {
        console.error('Erro ao atualizar benchmark:', error);
        return NextResponse.json(
            { error: 'Erro ao atualizar benchmark: ' + error.message },
            { status: 500 }
        );
    }
}
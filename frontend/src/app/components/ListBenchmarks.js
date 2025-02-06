'use client';

import { useState, useEffect } from 'react';
import axios from 'axios';

const ListBenchmarks = () => {
    const [benchmarks, setBenchmarks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        loadBenchmarks();
    }, []);

    const loadBenchmarks = async () => {
        try {
            setLoading(true);
            const response = await axios.get('/api/benchmarks');
            setBenchmarks(response.data);
            setError(null);
        } catch (err) {
            setError('Error fetching benchmarks: ' + err.message);
        } finally {
            setLoading(false);
        }
    };

    if (loading) {
        return (
            <div className="container mt-4">
                <div className="text-center">
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Carregando...</span>
                    </div>
                </div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="container mt-4">
                <div className="alert alert-danger" role="alert">
                    {error}
                </div>
            </div>
        );
    }

    return (
        <div className="container mt-4">
            <h2 className="mb-4">Lista de Benchmarks</h2>
            <table className="table table-striped table-hover">
                <thead className="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Pais 1</th>
                        <th>Pais 2</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {benchmarks.map((benchmark) => (
                        <tr key={benchmark.id}>
                            <td>{benchmark.id}</td>
                            <td>{benchmark.name}</td>
                            <td>{benchmark.country1}</td>
                            <td>{benchmark.country2}</td>
                            <td>
                                <button 
                                    className="btn btn-primary btn-sm me-2"
                                    onClick={() => console.log('Editar:', benchmark.id)}
                                >
                                    Editar
                                </button>
                                <button 
                                    className="btn btn-danger btn-sm"
                                    onClick={() => console.log('Deletar:', benchmark.id)}
                                >
                                    Deletar
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListBenchmarks;